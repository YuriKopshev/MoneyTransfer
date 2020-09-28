package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.CardChoicePage;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPageV1;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {


    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

    }

    @Test
    public void ShouldTransferMoneyFromSecondToFirst() {
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val startBalFirst = DashboardPage.getFirstCardBalance();
        val startBalSecond = DashboardPage.getSecondCardBalance();
        val cardChoice = new CardChoicePage().firstCardSelect();
        val cardTransferPage = cardChoice.moneyTransferSecondToFirst(DataHelper.getTransferMoneyInfoSecondToFirst());
        val expectResultFirst = startBalFirst + 200;
        val expectResultSecond = startBalSecond - 200;
        $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]").waitUntil(visible, 5000).shouldHave(text(String.valueOf(expectResultSecond)));
        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']").waitUntil(visible, 5000).shouldHave(text(String.valueOf(expectResultFirst)));
    }

    @Test
    void ShouldTransferMoneyFromFirstToSecond() {
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val startBalFirst = DashboardPage.getFirstCardBalance();
        val startBalSecond = DashboardPage.getSecondCardBalance();
        val cardChoice = new CardChoicePage().secondCardSelect();
        val cardTransferPage = cardChoice.moneyTransferFirstToSecond(DataHelper.getTransferMoneyInfoFirstToSecond());
        val expectResultFirst = startBalFirst - 200;
        val expectResultSecond = startBalSecond + 200;
        $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]").waitUntil(visible, 5000).shouldHave(text(String.valueOf(expectResultSecond)));
        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']").waitUntil(visible, 5000).shouldHave(text(String.valueOf(expectResultFirst)));

    }
}
