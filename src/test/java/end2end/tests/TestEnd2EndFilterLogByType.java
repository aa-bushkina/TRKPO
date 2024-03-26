package end2end.tests;

import end2end.pages.participant.LogsParticipantPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест проверяет работу фильтра по типу записей в журнале у участника
 */
public class TestEnd2EndFilterLogByType extends TestBase {
    private static final String DURATION = "25";
    private static final String ACTIVITY = "Утренняя зарядка";
    private static final String COMMENTS = "Простые упражнения";
    private static final String EMOTIONAL_TEXT = "Плакала и ела";
    private static final String EAT_TEXT = "Поела картошечку";

    @BeforeEach
    public void setUp() {
        logger.info("Регистрируем участника");
        registerParticipant();
    }

    @Test
    public void testEnd2EndAddSportLog() {
        logger.info("Тест проверяет работу фильтра по типу записей в журнале у участника");

        logger.info("Логинимся участником и добавляем записи каждого типа");
        LogsParticipantPage logsPage = getLoginPage()
                .login(LOGIN_PARTICIPANT, PASSWORD)
                .andReturnStartParticipantPage()
                .chooseSportType()
                .typeDuration(DURATION)
                .typeActivity(ACTIVITY)
                .chooseMiddleIntensity()
                .typeComment(COMMENTS)
                .clickSend()
                .clickOk()
                .chooseEmotionalType()
                .typeComment(EMOTIONAL_TEXT)
                .clickSend()
                .clickOk()
                .chooseEatType()
                .typeDescription(EAT_TEXT)
                .chooseBreakfastType()
                .clickSend()
                .clickOk()
                .goToLogs();
        logger.info("Проверяем, что по фильтру отображается верное количество записей");
        assertAll(
                () -> assertEquals(3, logsPage.getLogsCount(),
                        "Не сопадает количество записей с ожидаемым без фильтра"),
                () -> assertEquals(1, logsPage.chooseSportType().getLogsCount(),
                        "Не сопадает количество записей с ожидаемым по фильтру: спортивная активность"),
                () -> assertEquals(1, logsPage.chooseEatType().getLogsCount(),
                        "Не сопадает количество записей с ожидаемым по фильтру: прием пищи"),
                () -> assertEquals(1, logsPage.chooseEmotionalType().getLogsCount(),
                        "Не сопадает количество записей с ожидаемым по фильтру: эмоциональное состояние"),
                () -> assertEquals(3, logsPage.chooseAllType().getLogsCount(),
                        "Не сопадает количество записей с ожидаемым по фильтру: все")
        );

        logger.info("Тест прошел успешно");
    }
}
