package com.example.wx.demo.dto.button;

import com.example.wx.demo.dto.button.AbstractButton;

import java.util.ArrayList;
import java.util.List;

public class Button {

    private List<AbstractButton> button = new ArrayList<>();

    public List<AbstractButton> getButton() {
        return button;
    }

    public void setButton(List<AbstractButton> button) {
        this.button = button;
    }
}
