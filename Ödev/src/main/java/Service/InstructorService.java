package Service;


import java.util.List;

public interface InstructorService {
    InstructorResponse add(InstructorRequest request);
    InstructorResponse update(Long id, InstructorRequest request);
    void delete(Long id);
    InstructorResponse getById(Long id);
    List<InstructorResponse> getAll();
}
