package ru.mail.polis.homework.oop.adapter;

/**
 * После имплементации раскоментировать тест в ru.mail.polis.homework.oop.adapter.ProductPriceParsServiceTest
 * для проверки правильной имплементации
 */
public class JsonCsvDataAdapter extends CsvData {

    public JsonCsvDataAdapter(String jsonText) {
        super(jsonText);
    }

    public String getText() {
       return super.getText().replace("{", "").replace("}", "").replace("\"", "").replace(",", "").replace(":", ",");
    }
}
