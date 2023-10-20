package net.pmkjun.planetskilltimer.gui.widget;

import net.minecraft.client.gui.navigation.GuiNavigationPath;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class Slider extends SliderWidget {
    Text text;

    public Slider(int x, int y, int width, int height, Text text, double value) {
        super(x, y, width, height, text, value);
        this.text = text;
    }

    @Override
    protected void updateMessage() {
        this.setMessage(Text.literal("").append(text).append(String.valueOf(this.value)));
    }

    @Override
    protected void applyValue() {

    }
}
