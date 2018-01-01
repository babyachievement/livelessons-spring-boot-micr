package demo.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Data JPA tests for {@link User}.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserEntityTest {

    private static final VehicleIdentificationNumber VIN = new VehicleIdentificationNumber(
            "01234567890123456");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private TestEntityManager entityManager;  // @DataJpaTest 创建

    @Test
    public void createWhenUsernameIsNullShouldThrowException() throws Exception {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Username must not be empty");
        new User(null, VIN);
    }

    @Test
    public void createWhenUsernameIsEmptyShouldThrowException() throws Exception {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Username must not be empty");
        new User("", VIN);
    }

    @Test
    public void createWhenVinIsNullShouldThrowException() throws Exception {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("VIN must not be null");
        new User("donald", null);
    }

    @Test
    public void saveShouldPersistData() throws Exception {
        User user = this.entityManager.persistFlushFind(new User("donald", VIN));
        assertThat(user.getUsername()).isEqualTo("donald");
        assertThat(user.getVin()).isEqualTo(VIN);
    }

}
