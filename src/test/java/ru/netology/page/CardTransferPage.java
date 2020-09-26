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

    public CardTransferPage() {
        amount.shouldBe(visible);
    }

    public DashboardPage moneyTransferSecondToFirst(DataHelper.Transfer transfer){
        amount.setValue(transfer.getSum());
        fromField.setValue(transfer.getFrom());
        $$("[type='button']").find(exactText("Пополнить")).click();
        return new DashboardPage();

    }
    public DashboardPage moneyTransferFirstToSecond(DataHelper.Transfer transfer){
        amount.setValue(transfer.getSum());
        fromField.setValue(transfer.getFrom());
        $$("[type='button']").find(exactText("Пополнить")).click();
        return new DashboardPage();

    }

}

