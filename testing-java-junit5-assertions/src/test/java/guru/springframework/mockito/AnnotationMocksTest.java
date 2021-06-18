package guru.springframework.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Map;

public class AnnotationMocksTest {

    @Mock
    private Map<String, String> mapMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(mapMock.get("name")).thenReturn("tientcheu");
    }

    @Test
    void testMock() {
        Assertions.assertEquals("tientcheu", mapMock.get("name"));
    }
}
