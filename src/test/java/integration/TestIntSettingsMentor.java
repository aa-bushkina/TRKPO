package integration;

import com.cygans.Application;
import com.cygans.database.controllers.SettingsController;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorRepository;
import com.cygans.security.db.RoleEnum;
import integration.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест проверяет, что при вызове метода обновления личных данных в контроллере с валидными полями
 * для роли ментор – данные изменяются в БД
 */
@SpringBootTest(classes = Application.class)
public class TestIntSettingsMentor extends BaseTest {
    private static final String NEW_FIRSTNAME = "Марина";
    private static final String NEW_LASTNAME = "Коротаева";
    private static final String NEW_PHONE = "+79383178273";
    private static final String NEW_GENDER = "Муж";
    private static final LocalDate NEW_BIRTHDAY = LocalDate.now().minusYears(5);

    @Autowired
    private SettingsController settingsController;

    @Autowired
    private MentorRepository mentorRepository;

    @BeforeEach
    public void setUp() {
        logger.info("Создаем ментора");
        registerMentor();
    }

    @Test
    public void testIntSettingsParticipant() {
        logger.info("Тест проверяет, что при вызове метода обновления личных данных в контроллере " +
                "с валидными полями для роли ментор – данные изменяются в БД");

        logger.info("Вызываем метод обновления данных ментор");
        settingsController.updateInfoUser(RoleEnum.MENTOR, NEW_FIRSTNAME, NEW_LASTNAME, LOGIN, NEW_PHONE,
                NEW_BIRTHDAY, NEW_GENDER, null, null, null, null, null);

        logger.info("Проверяем, что в БД изменились данные участника в таблице Participant");
        Mentor participant = mentorRepository.getMentorByLogin(LOGIN);
        assertAll(
                () -> assertEquals(NEW_FIRSTNAME, participant.getFirstName(),
                        "Значение firstname не совпадает с установленным"),
                () -> assertEquals(NEW_LASTNAME, participant.getLastName(),
                        "Значение lastname не совпадает с установленным"),
                () -> assertEquals(NEW_BIRTHDAY, participant.getBirthday(),
                        "Значение birthday не совпадает с установленным"),
                () -> assertEquals(NEW_GENDER, participant.getGender(),
                        "Значение gender не совпадает с установленным"),
                () -> assertEquals(NEW_PHONE, participant.getPhone(),
                        "Значение phone не совпадает с установленным")
        );

        logger.info("Тест успешно пройден");
    }
}
