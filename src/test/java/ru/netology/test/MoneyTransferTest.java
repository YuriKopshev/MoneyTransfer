package ru.netology.test;


import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPageV1;

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
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val startBalFirst = dashboardPage.getFirstCardBalance();
        val startBalSecond = dashboardPage.getSecondCardBalance();
        val transferPage = dashboardPage.chooseSecondCard();
        transferPage.transferMoney(DataHelper.getFirstCardNumber(), sum);
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
        transferPage.transferMoney(DataHelper.getSecondCardNumber(), sum);
        val actualResultFirst = dashboardPage.getFirstCardBalance();
        val actualResultSecond = dashboardPage.getSecondCardBalance();
        val expectResultFirst = startBalFirst + sum;
        val expectResultSecond = startBalSecond - sum;
        assertEquals(expectResultFirst, actualResultFirst);
        assertEquals(expectResultSecond, actualResultSecond);
    }

    @Test
    public void shouldTransferMoneyMoreAccountSum() {
        String expectResult = "Недостаточно средств для совершения операции";
        int sum = 50000;
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val transferPage = dashboardPage.chooseFirstCard();
        transferPage.transferMoney(DataHelper.getSecondCardNumber(), sum);
        transferPage.searchErrorMessage();

    }


}
