package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.CardChoicePage;
import ru.netology.page.LoginPageV1;
import ru.netology.page.LoginPageV3;

import javax.xml.xpath.XPath;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.visible;
class MoneyTransferTest {

    String sumResult = "9800";
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
    void ShouldTransferMoneyFromFirstToSecond(){
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val cardChoice = new CardChoicePage().firstCardSelect();
        val cardTransferPage = cardChoice.moneyTransferFirstToSecond(DataHelper.getTransferMoneyInfoFirstToSecond());
        $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]").waitUntil(visible, 5000).shouldHave(text(sumResult));
    }

    @Test
    void ShouldTransferMoneyFromSecondToFirst(){
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val cardChoice = new CardChoicePage().secondCardSelect();
        val cardTransferPage = cardChoice.moneyTransferSecondToFirst(DataHelper.getTransferMoneyInfoSecondToFirst());
        $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]").waitUntil(visible, 5000).shouldHave(tex);
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
