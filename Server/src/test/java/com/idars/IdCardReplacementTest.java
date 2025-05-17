package com.idars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

// Mock model classes based on your models package
class Student {
    private String id;
    private String password;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

class Request {
    private String id;
    private String studentId;
    private String type;
    private String idImage;
    private String receiptImage;
    private String status;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getIdImage() { return idImage; }
    public void setIdImage(String idImage) { this.idImage = idImage; }
    public String getReceiptImage() { return receiptImage; }
    public void setReceiptImage(String receiptImage) { this.receiptImage = receiptImage; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

// Mock repository interfaces
interface StudentRepository {
    Optional<Student> findById(String id);
}

interface RequestRepository {
    Request save(Request request);
    Optional<Request> findById(String id);
}

// Mock controller classes based on your controller package
class StudentController {
    private final StudentRepository studentRepository;
    private final RequestRepository requestRepository;

    StudentController(StudentRepository studentRepository, RequestRepository requestRepository) {
        this.studentRepository = studentRepository;
        this.requestRepository = requestRepository;
    }

   boolean login(String studentId, String password) {
    return studentRepository.findById(studentId)
            .filter(s -> s.getPassword() != null && com.idars.IdCardReplacementTest.MockPasswordEncoder.matches(password, s.getPassword()))
            .isPresent();
}

    String submitRequest(Request request) {
        Request savedRequest = requestRepository.save(request);
        return "REQ" + savedRequest.getId(); // Simplified ID generation
    }
}

class FinanceDepartmentController {
    private final RequestRepository requestRepository;

    FinanceDepartmentController(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    boolean approveRequest(String requestId) {
        return requestRepository.findById(requestId)
                .filter(r -> "Pending".equals(r.getStatus()))
                .map(r -> {
                    r.setStatus("Approved");
                    requestRepository.save(r);
                    return true;
                })
                .orElse(false);
    }
}

public class IdCardReplacementTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private RequestRepository requestRepository;

    @InjectMocks
    private StudentController studentController;

    @InjectMocks
    private FinanceDepartmentController financeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        studentController = new StudentController(studentRepository, requestRepository);
    }

    @Test
    void testStudentLogin_ValidCredentials_Success() {
        String studentId = "123456";
        String password = "validPassword";
        Student student = new Student();
        student.setId(studentId);
        student.setPassword(MockPasswordEncoder.encode(password));
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        boolean result = studentController.login(studentId, password);

        assertTrue(result, "Login should succeed with valid credentials");
        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    void testStudentLogin_InvalidPassword_Failure() {
        String studentId = "123456";
        String password = "wrongPassword";
        Student student = new Student();
        student.setId(studentId);
        student.setPassword(MockPasswordEncoder.encode("validPassword"));
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        boolean result = studentController.login(studentId, password);

        assertFalse(result, "Login should fail with invalid password");
        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    void testSubmitIDRequest_ValidRequest_Success() {
        String studentId = "123456";
        Request request = new Request();
        request.setStudentId(studentId);
        request.setType("Renewal");
        request.setIdImage("damaged_id.jpg");
        request.setReceiptImage("receipt.jpg");
        when(requestRepository.save(any(Request.class))).thenReturn(request);

        String requestId = studentController.submitRequest(request);

        assertNotNull(requestId, "Request ID should be returned");
        assertTrue(requestId.startsWith("REQ"), "Request ID should start with 'REQ'");
        verify(requestRepository, times(1)).save(any(Request.class));
    }

    @Test
    void testApproveRequest_ValidRequest_Success() {
        String requestId = "REQ001";
        Request request = new Request();
        request.setId(requestId);
        request.setStatus("Pending");
        when(requestRepository.findById(requestId)).thenReturn(Optional.of(request));

        boolean result = financeController.approveRequest(requestId);

        assertTrue(result, "Approval should succeed");
        assertEquals("Approved", request.getStatus(), "Request status should be updated");
        verify(requestRepository, times(1)).save(request);
    }

    public static class MockPasswordEncoder {
        public static String encode(String password) {
            return "encoded:" + password;
        }
        public static boolean matches(String rawPassword, String encodedPassword) {
            return encodedPassword.equals("encoded:" + rawPassword);
        }
    }
}