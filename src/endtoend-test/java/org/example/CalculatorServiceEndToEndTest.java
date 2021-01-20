package org.example;

import org.junit.jupiter.api.*;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.lang.reflect.Field;
import java.util.Map;

@SpringJUnitConfig
@Testcontainers
public class CalculatorServiceEndToEndTest {

    private static final DockerImageName ADDITION_SERVICE = DockerImageName.
            parse("registry.hub.docker.com/addition-service:latest")
            .asCompatibleSubstituteFor("addition-service");
    private static final DockerImageName SUBTRACTION_SERVICE = DockerImageName
            .parse("registry.hub.docker.com/subtraction-service:latest")
            .asCompatibleSubstituteFor("subtraction-service");
    private static final DockerImageName CALCULATOR_SERVICE = DockerImageName
            .parse("registry.hub.docker.com/subtraction-service:latest")
            .asCompatibleSubstituteFor("subtraction-service");

    static {
        setRyukDisabledInEnv();
    }

    private static Network network;
    private static GenericContainer<?> additionContainer;
    private static GenericContainer<?> subtractionContainer;
    private static GenericContainer<?> calculatorContainer;

    @BeforeAll
    static void setUp() {
        network = Network.newNetwork();
        additionContainer = new GenericContainer<>(ADDITION_SERVICE)
                .withExposedPorts(8070)
                .withNetwork(network)
                .withEnv("SERVER_PORT", "8070")
                .withEnv("SERVER_ADDRESS", "localhost");

        subtractionContainer = new GenericContainer<>(SUBTRACTION_SERVICE)
                .withExposedPorts(8071)
//                .withExtraHost("subtraction-service","10.150.17.73")
                .withNetwork(network)
                .withEnv("SERVER_PORT", "8071")
                .withEnv("SERVER_ADDRESS", "localhost");

        calculatorContainer = new GenericContainer<>(CALCULATOR_SERVICE)
                .withExposedPorts(8072)
                .withNetwork(network)
                .withEnv("SERVER_PORT", "8072")
                .withEnv("SERVER_ADDRESS", "localhost");

        additionContainer.start();
        subtractionContainer.start();
    }

    @AfterAll
    static void tearDown() {
        additionContainer.close();
        subtractionContainer.close();
        calculatorContainer.close();
        network.close();
    }

    @Test
    public void additionTest() {
        System.out.println();
    }

    @Test
    public void subtractionTest() {
        System.out.println();
    }

    @Test
    public void calculatorTest() {
        System.out.println();
    }

    private static void setRyukDisabledInEnv() {
        try {
            Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
            Field theCaseInsensitiveEnvironment = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
            theCaseInsensitiveEnvironment.setAccessible(true);
            Map<String, String> caseEnv = (Map<String, String>) theCaseInsensitiveEnvironment.get(null);
            caseEnv.put("TESTCONTAINERS_RYUK_DISABLED", "true");
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
