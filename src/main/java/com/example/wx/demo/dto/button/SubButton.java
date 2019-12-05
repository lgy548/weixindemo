package com.example.wx.demo.dto.button;

import java.util.ArrayList;
import java.util.List;

public class SubButton extends AbstractButton {

    private List<AbstractButton> sub_button = new ArrayList<>();

    public List<AbstractButton> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<AbstractButton> sub_button) {
        this.sub_button = sub_button;
    }

    public SubButton(String name) {
        super(name);
    }
}
