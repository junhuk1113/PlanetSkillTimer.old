package net.pmkjun.planetskilltimer.config;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.pmkjun.planetskilltimer.PlanetSkillTimerClient;
import net.pmkjun.planetskilltimer.gui.widget.Slider;

public class ConfigScreen extends Screen{

    private MinecraftClient mc;
    private PlanetSkillTimerClient client;
    private final Screen parentScreen;

    private ButtonWidget toggleSkillTimerButton;
    private Slider slider;

    public ConfigScreen(Screen parentScreen) {
        super(Text.literal("스킬 타이머 설정"));
        this.parentScreen = parentScreen;
        this.mc = MinecraftClient.getInstance();
        this.client = PlanetSkillTimerClient.getInstance();
    }
    @Override
    protected void init() {
        String toggleskilltimer;
        if(client.data.toggleSkilltimer){
            toggleskilltimer = "planetskilltimer.config.enable";
        }
        else{
            toggleskilltimer = "planetskilltimer.config.disable";
        }
        toggleSkillTimerButton = ButtonWidget.builder(Text.translatable(toggleskilltimer),button -> {
            toggleSkilltimer();
        }).dimensions(mc.getWindow().getScaledWidth() / 2 - 75,mc.getWindow().getScaledHeight()/2, 150,20).build();
        this.addDrawableChild(toggleSkillTimerButton);

        ButtonWidget exitButton = ButtonWidget.builder(Text.translatable("planetskilltimer.config.exit"), button -> {
            mc.setScreen(parentScreen);
        }).dimensions(mc.getWindow().getScaledWidth() / 2 - 35, mc.getWindow().getScaledHeight() - 25, 70, 20).build();
        this.addDrawableChild(exitButton);

        slider = new Slider(mc.getWindow().getScaledWidth() / 2 - 35, mc.getWindow().getScaledHeight() - 25+(20+2),150,20,Text.literal("슬라이더 : "),0);
        this.addDrawableChild(slider);
    }
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        slider.render(context,mouseX,mouseY,delta);
        super.render(context, mouseX, mouseY, delta);
    }

    private void toggleSkilltimer(){
        if(client.data.toggleSkilltimer){
            toggleSkillTimerButton.setMessage(Text.translatable("planetskilltimer.config.disable"));
            client.data.toggleSkilltimer = false;
            client.configManage.save();
        }
        else{
            toggleSkillTimerButton.setMessage(Text.translatable("planetskilltimer.config.enable"));
            client.data.toggleSkilltimer = true ;
            client.configManage.save();
        }
    }
}