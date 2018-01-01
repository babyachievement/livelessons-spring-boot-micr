package demo.domain;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class VehicleIdentificationNumberTest {

    private static final String SAMPLE_VIN = "01234567890123456";

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void createWhenVinIsNullShouldThrowException() throws Exception {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("VIN must not be null");
        new VehicleIdentificationNumber(null);
    }

    @Test
    public void createWhenVinIsMoreThan17CharsShouldThrowException() throws Exception {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("VIN must be exactly 17 characters");
        new VehicleIdentificationNumber("012345678901234567");
    }

    @Test
    public void createWhenVinIsLessThan17CharsShouldThrowException() throws Exception {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("VIN must be exactly 17 characters");
        new VehicleIdentificationNumber("0123456789012345");
    }

    @Test
    public void toStringShouldReturnVin() throws Exception {
        VehicleIdentificationNumber vin = new VehicleIdentificationNumber(SAMPLE_VIN);
        assertThat(vin.toString()).isEqualTo(SAMPLE_VIN);
    }

    @Test
    public void equalsAndHashCodeShouldBeBasedOnVin() throws Exception {
        VehicleIdentificationNumber vin1 = new VehicleIdentificationNumber(SAMPLE_VIN);
        VehicleIdentificationNumber vin2 = new VehicleIdentificationNumber(SAMPLE_VIN);
        VehicleIdentificationNumber vin3 = new VehicleIdentificationNumber(
                "00000000000000000");
        assertThat(vin1.hashCode()).isEqualTo(vin2.hashCode());
        assertThat(vin1).isEqualTo(vin1).isEqualTo(vin2).isNotEqualTo(vin3);
    }

}