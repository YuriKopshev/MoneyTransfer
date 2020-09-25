package ru.netology.page;

import static com.codeborne.selenide.Selenide.$;

public class CardChoicePage {
    public CardTransferPage firstCardSelect() {
     $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"] .button").click();
     return new CardTransferPage();
    }

    public CardTransferPage secondCardSelect() {
    $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"] .button").click();
    return new CardTransferPage();
    }
}
