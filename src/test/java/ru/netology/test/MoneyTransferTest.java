package ru.netology.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.CardTransferPage;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPageV1;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    private SelenideElement amount = $("[data-test-id=amount] input.input__control");

    @Test
    public void shouldTransferMoneyFromSecondToFirst() {
        int sum = 200;
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val startBalFirst = dashboardPage.getFirstCardBalance();
        val startBalSecond = dashboardPage.getSecondCardBalance();
        val transferPage = dashboardPage.chooseSecondCard();
        amount.setValue(String.valueOf(sum));
        transferPage.CardSelect(DataHelper.getFirstCardNumber());
        val actualResultFirst = dashboardPage.getFirstCardBalance();
        val actualResultSecond = dashboardPage.getSecondCardBalance();
        val expectResultFirst = startBalFirst - sum;
        val expectResultSecond = startBalSecond + sum;
        assertEquals(expectResultFirst, actualResultFirst);
        assertEquals(expectResultSecond, actualResultSecond);
    }

    @Test
    public void shouldTransferMoneyFromFirstToSecond() {
        int sum = 300;
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val startBalFirst = dashboardPage.getFirstCardBalance();
        val startBalSecond = dashboardPage.getSecondCardBalance();
        val transferPage = dashboardPage.chooseFirstCard();
        amount.setValue(String.valueOf(sum));
        transferPage.CardSelect(DataHelper.getSecondCardNumber());
        val actualResultFirst = dashboardPage.getFirstCardBalance();
        val actualResultSecond = dashboardPage.getSecondCardBalance();
        val expectResultFirst = startBalFirst + sum;
        val expectResultSecond = startBalSecond - sum;
        assertEquals(expectResultFirst, actualResultFirst);
        assertEquals(expectResultSecond, actualResultSecond);
    }

    @Test
    public void shouldTransferMoneyMoreAccountSum() {
        int sum = 50000;
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val transferPage = dashboardPage.chooseFirstCard();
        amount.setValue(String.valueOf(sum));
        transferPage.CardSelect(DataHelper.getSecondCardNumber());
        $("[data-test-id=dashboard]").waitUntil(visible, 5000).shouldHave(text("Недостаточно средств для совершения операции"));
    }


}
