package integration;

import com.cygans.Application;
import com.cygans.database.participant.Participant;
import com.cygans.security.db.RoleEnum;
import integration.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест проверяет, что при вызове метода обновления личных данных в контроллере с валидными полями
 * для роли участник – данные изменяются в БД
 */
@SpringBootTest(classes = Application.class)
public class TestIntSettingsParticipant extends BaseTest {
    private static final String NEW_FIRSTNAME = "Марина";
    private static final String NEW_LASTNAME = "Коротаева";
    private static final String NEW_PHONE = "+79383178273";
    private static final String NEW_GENDER = "Муж";
    private static final LocalDate NEW_BIRTHDAY = LocalDate.now().minusYears(5);
    private static final int NEW_HEIGHT = 70;
    private static final int NEW_WEIGHT = 70;
    private static final int NEW_BREAST = 70;
    private static final int NEW_WAIST = 70;
    private static final int NEW_HIPS = 70;
    @BeforeEach
    public void setUp() {
        logger.info("Создаем участника");
        registerParticipant();
    }

    @Test
    public void testIntSettingsParticipant() {
        logger.info("Тест проверяет, что при вызове метода обновления личных данных в контроллере " +
                "с валидными полями для роли участник – данные изменяются в БД");

        logger.info("Вызываем метод обновления данных участника");
        settingsController.updateInfoUser(RoleEnum.PARTICIPANT, NEW_FIRSTNAME, NEW_LASTNAME, LOGIN_PARTICIPANT, NEW_PHONE,
                NEW_BIRTHDAY, NEW_GENDER, NEW_HEIGHT, NEW_WEIGHT, NEW_BREAST, NEW_WAIST, NEW_HIPS);

        logger.info("Проверяем, что в БД изменились данные участника в таблице Participant");
        Participant participant = participantRepository.getParticipantByLogin(LOGIN_PARTICIPANT);
        assertAll(
                () -> assertEquals(NEW_HEIGHT, participant.getHeight(),
                        "Значение height не совпадает с установленным"),
                () -> assertEquals(NEW_BREAST, participant.getBreast(),
                        "Значение breast не совпадает с установленным"),
                () -> assertEquals(NEW_HIPS, participant.getHips(),
                        "Значение hips не совпадает с установленным"),
                () -> assertEquals(NEW_WAIST, participant.getWaist(),
                        "Значение waist не совпадает с установленным"),
                () -> assertEquals(NEW_WEIGHT, participant.getWeight(),
                        "Значение weight не совпадает с установленным"),
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
