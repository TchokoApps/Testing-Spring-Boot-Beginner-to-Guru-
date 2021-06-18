package guru.springframework.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class JunitExtensionTest {
    @Mock
    private Map<String, String> mapMock;

    @BeforeEach
    public void setUp() {
        Mockito.when(mapMock.get("name")).thenReturn("tientcheu");
    }

    @Test
    void testMock() {
        Assertions.assertEquals("tientcheu", mapMock.get("name"));
    }
}
