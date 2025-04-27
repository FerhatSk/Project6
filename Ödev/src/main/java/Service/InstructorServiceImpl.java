package Service;

import Entity.Instructor;
import Repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public InstructorResponse add(InstructorRequest request) {
        Instructor instructor = new Instructor();
        mapRequestToEntity(request, instructor);
        Instructor saved = instructorRepository.save(instructor);
        return mapEntityToResponse(saved);
    }

    @Override
    public InstructorResponse update(Long id, InstructorRequest request) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
        mapRequestToEntity(request, instructor);
        Instructor updated = instructorRepository.save(instructor);
        return mapEntityToResponse(updated);
    }

    @Override
    public void delete(Long id) {
        instructorRepository.deleteById(id);
    }

    @Override
    public InstructorResponse getById(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
        return mapEntityToResponse(instructor);
    }

    @Override
    public List<InstructorResponse> getAll() {
        return instructorRepository.findAll()
                .stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    private void mapRequestToEntity(InstructorRequest request, Instructor instructor) {
        instructor.setUsername(request.getUsername());
        instructor.setFirstName(request.getFirstName());
        instructor.setLastName(request.getLastName());
        instructor.setDateOfBirth(request.getDateOfBirth());
        instructor.setNationalIdentity(request.getNationalIdentity());
        instructor.setEmail(request.getEmail());
        instructor.setPassword(request.getPassword());
        instructor.setCompanyName(request.getCompanyName());
    }

    private InstructorResponse mapEntityToResponse(Instructor instructor) {
        InstructorResponse response = new InstructorResponse();
        response.setId(instructor.getId());
        response.setUsername(instructor.getUsername());
        response.setCompanyName(instructor.getCompanyName());
        return response;
    }
}
