package ru.netology.page;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardTransferPage {
    private SelenideElement amount = $("[data-test-id=amount] input.input__control");
    private SelenideElement fromField = $("[data-test-id=from] input.input__control");
    private SelenideElement secondCard =  $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"] .button");
    private String sumTransfer;


    public String getSumTransfer() {
        return sumTransfer;
    }

    public void setSumTransfer(String sumTransfer) {
        this.sumTransfer = sumTransfer;
    }

    public DashboardPage secondCardSelect(DataHelper.CardInfo cardInfo){
        secondCard.click();
        amount.setValue(getSumTransfer());
        fromField.setValue(cardInfo.getCardNumber());
        $$("[type='button']").find(exactText("Пополнить")).click();
        return new DashboardPage();

    }
    public DashboardPage firstCardSelect(DataHelper.CardInfo cardInfo){
        $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"] .button").click();
        amount.setValue(getSumTransfer());
        fromField.setValue(cardInfo.getCardNumber());
        $$("[type='button']").find(exactText("Пополнить")).click();
        return new DashboardPage();

    }

}

