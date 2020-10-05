package ru.netology.page;


import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardTransferPage {
    private SelenideElement amountField = $("[data-test-id=amount] input.input__control");
    private SelenideElement fromField = $("[data-test-id=from] input.input__control");


    public void transferMoney(DataHelper.CardInfo cardInfo, int sum) {
        amountField.setValue(String.valueOf(sum));
        fromField.setValue(cardInfo.getCardNumber());
        $$("[type='button']").find(exactText("Пополнить")).click();
    }

    public void searchErrorMessage() {
        $("[data-test-id=dashboard]").waitUntil(visible, 5000).
                shouldHave(text("Недостаточно средств для совершения операции"));
    }


}

