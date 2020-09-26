package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.CardChoicePage;
import ru.netology.page.LoginPageV1;
import ru.netology.page.LoginPageV3;

import javax.xml.xpath.XPath;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
//    val loginPage = open("http://localhost:9999", LoginPageV1.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void ShouldTransferMoneyFromSecondToFirst(){
        String sumResultFirst = "10000";
        String sumResultSecond = "10000";
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val cardChoice = new CardChoicePage().firstCardSelect();
        val cardTransferPage = cardChoice.moneyTransferSecondToFirst(DataHelper.getTransferMoneyInfoSecondToFirst());
        $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]").waitUntil(visible, 5000).shouldHave(text(sumResultSecond));
        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']").waitUntil(visible, 5000).shouldHave(text(sumResultFirst));
    }

    @Test
    void ShouldTransferMoneyFromFirstToSecond(){
        String sumResultFirst = "9800";
        String sumResultSecond = "10200";
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val cardChoice = new CardChoicePage().secondCardSelect();
        val cardTransferPage = cardChoice.moneyTransferFirstToSecond(DataHelper.getTransferMoneyInfoFirstToSecond());
        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']").waitUntil(visible, 5000).shouldHave(text(sumResultFirst));
        $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]").waitUntil(visible, 5000).shouldHave(text(sumResultSecond));
    }





//    @Test
//    void shouldTransferMoneyBetweenOwnCardsV3() {
//        val loginPage = open("http://localhost:9999", LoginPageV3.class);
//        val authInfo = DataHelper.getAuthInfo();
//        val verificationPage = loginPage.validLogin(authInfo);
//        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//        verificationPage.validVerify(verificationCode);
//    }

}
