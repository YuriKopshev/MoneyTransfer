package ru.netology.test;

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

    @Test
    public void shouldTransferMoneyFromSecondToFirst() {
        int sum = 200;
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage =  verificationPage.validVerify(verificationCode);
        val startBalFirst = dashboardPage.getFirstCardBalance();
        val startBalSecond = dashboardPage.getSecondCardBalance();
        CardTransferPage page = new CardTransferPage();
        page.setSumTransfer("200");
        val cardTransferPage = page.secondCardSelect(DataHelper.getFirstCardNumber());
        val actualResultFirst = cardTransferPage.getFirstCardBalance();
        val actualResultSecond = cardTransferPage.getSecondCardBalance();
        val expectResultFirst = startBalFirst - sum;
        val expectResultSecond = startBalSecond + sum;
        assertEquals(expectResultFirst,actualResultFirst);
        assertEquals(expectResultSecond,actualResultSecond);
    }

    @Test
    public void shouldTransferMoneyFromFirstToSecond() {
        int sum = 200;
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage =  verificationPage.validVerify(verificationCode);
        val startBalFirst = dashboardPage.getFirstCardBalance();
        val startBalSecond = dashboardPage.getSecondCardBalance();
        CardTransferPage page = new CardTransferPage();
        page.setSumTransfer("200");
        val cardTransferPage = page.firstCardSelect(DataHelper.getSecondCardNumber());
        val actualResultFirst = cardTransferPage.getFirstCardBalance();
        val actualResultSecond = cardTransferPage.getSecondCardBalance();
        val expectResultFirst = startBalFirst + sum;
        val expectResultSecond = startBalSecond - sum;
        assertEquals(expectResultFirst,actualResultFirst);
        assertEquals(expectResultSecond,actualResultSecond);
    }

    @Test
    public void shouldTransferInvalidAmountMoney() {
        int sum = 50000;
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage =  verificationPage.validVerify(verificationCode);
        val startBalFirst = dashboardPage.getFirstCardBalance();
        val startBalSecond = dashboardPage.getSecondCardBalance();
        CardTransferPage page = new CardTransferPage();
        page.setSumTransfer("50000");
        val cardTransferPage = page.firstCardSelect(DataHelper.getSecondCardNumber());
        val actualResultFirst = cardTransferPage.getFirstCardBalance();
        val actualResultSecond = cardTransferPage.getSecondCardBalance();
        val expectResultFirst = startBalFirst + sum;
        val expectResultSecond = startBalSecond - sum;
        assertEquals(expectResultFirst,actualResultFirst);
        assertEquals(expectResultSecond,actualResultSecond);
    }




}
