package demo.service;

import demo.domain.VehicleIdentificationNumber;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpServerErrorException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Tests for {@link RemoteVehicleDetailsService}.
 */
@RunWith(SpringRunner.class)
@RestClientTest({ RemoteVehicleDetailsService.class,
        RemoteVehicleDetailsServiceProperties.class })
@TestPropertySource(properties = "vehicle.service.root-url=http://example.com/")
public class RemoteVehicleDetailsServiceTest {

    private static final String VIN = "01234567890123456";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private MockRestServiceServer server; //

    @Autowired
    private RemoteVehicleDetailsService service;

    @Test
    public void getVehicleDetailsWhenVinIsNullShouldThrowException() throws Exception {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("VIN must not be null");
        this.service.getVehicleDetails(null);
    }

    @Test
    public void getVehicleDetailsWhenResultIsSuccessShouldReturnDetails()
            throws Exception {
        this.server.expect(requestTo("http://example.com/vehicle/" + VIN + "/details"))
                .andRespond(withSuccess(getClassPathResource("vehicledetails.json"),
                        MediaType.APPLICATION_JSON));
        VehicleDetails details = this.service
                .getVehicleDetails(new VehicleIdentificationNumber(VIN));
        assertThat(details.getMake()).isEqualTo("Honda");
        assertThat(details.getModel()).isEqualTo("Civic");
    }

    @Test
    public void getVehicleDetailsWhenResultIsNotFoundShouldThrowException()
            throws Exception {
        this.server.expect(requestTo("http://example.com/vehicle/" + VIN + "/details"))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));
        this.thrown.expect(VehicleIdentificationNumberNotFoundException.class);
        this.service.getVehicleDetails(new VehicleIdentificationNumber(VIN));
    }

    @Test
    public void getVehicleDetailsWhenResultIServerErrorShouldThrowException()
            throws Exception {
        this.server.expect(requestTo("http://example.com/vehicle/" + VIN + "/details"))
                .andRespond(withServerError());
        this.thrown.expect(HttpServerErrorException.class);
        this.service.getVehicleDetails(new VehicleIdentificationNumber(VIN));
    }

    private ClassPathResource getClassPathResource(String path) {
        return new ClassPathResource(path, getClass());
    }

}
