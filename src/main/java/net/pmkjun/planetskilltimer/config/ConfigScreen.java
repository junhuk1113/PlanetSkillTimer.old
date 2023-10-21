package net.pmkjun.planetskilltimer.config;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.pmkjun.planetskilltimer.PlanetSkillTimerClient;
import net.pmkjun.planetskilltimer.gui.widget.Slider;

public class ConfigScreen extends Screen{

    private MinecraftClient mc;
    private PlanetSkillTimerClient client;
    private final Screen parentScreen;

    private ButtonWidget toggleSkillTimerButton;
    private ButtonWidget[] toggleSkillsButton = new ButtonWidget[4];
    String[] SkillList = {"farming","felling","mining","digging"};
    private Slider XPosSlider;
    private Slider YPosSlider;
    private int width, height;

    public ConfigScreen(Screen parentScreen) {
        super(Text.literal("스킬 타이머 설정"));
        this.parentScreen = parentScreen;
        this.mc = MinecraftClient.getInstance();
        this.client = PlanetSkillTimerClient.getInstance();

        this.width = 150;
        this.height = (20 + 2) * 8;
    }
    @Override
    protected void init() {
        Text text;
        if(client.data.toggleSkilltimer){
            text = Text.translatable("planetskilltimer.config.skilltimer").append(
                    Text.translatable("planetskilltimer.config.enable").getWithStyle(Style.EMPTY.withFormatting(Formatting.GREEN).withBold(true)).get(0));
        }
        else{
            text = Text.translatable("planetskilltimer.config.skilltimer").append(
                    Text.translatable("planetskilltimer.config.disable").getWithStyle(Style.EMPTY.withFormatting(Formatting.RED).withBold(true)).get(0));
        }
        toggleSkillTimerButton = ButtonWidget.builder(text,button -> {
            toggleSkilltimer();
        }).dimensions(getRegularX(),getRegularY(), 150,20).build();
        this.addDrawableChild(toggleSkillTimerButton);

        for(int i = 0; i < 4 ; i++){
            if(client.data.toggleSkills[i]){
                text = Text.translatable("planetskilltimer.config."+SkillList[i]).append(
                        Text.translatable("planetskilltimer.config.enable").getWithStyle(Style.EMPTY.withFormatting(Formatting.GREEN).withBold(true)).get(0));
            }
            else{
                text = Text.translatable("planetskilltimer.config."+SkillList[i]).append(
                        Text.translatable("planetskilltimer.config.disable").getWithStyle(Style.EMPTY.withFormatting(Formatting.RED).withBold(true)).get(0));
            }
            switch (i){
                case 0:
                    toggleSkillsButton[i] = ButtonWidget.builder(text,button -> {
                        toggleSkills(0);
                    }).dimensions(getRegularX(),getRegularY()+(20+2)*(i+1), 150,20).build();
                    break;
                case 1:
                    toggleSkillsButton[i] = ButtonWidget.builder(text,button -> {
                        toggleSkills(1);
                    }).dimensions(getRegularX(),getRegularY()+(20+2)*(i+1), 150,20).build();
                    break;
                case 2:
                    toggleSkillsButton[i] = ButtonWidget.builder(text,button -> {
                        toggleSkills(2);
                    }).dimensions(getRegularX(),getRegularY()+(20+2)*(i+1), 150,20).build();
                    break;
                case 3:
                    toggleSkillsButton[i] = ButtonWidget.builder(text,button -> {
                        toggleSkills(3);
                    }).dimensions(getRegularX(),getRegularY()+(20+2)*(i+1), 150,20).build();
                    break;

            }

            this.addDrawableChild(toggleSkillsButton[i]);
        }

        ButtonWidget exitButton = ButtonWidget.builder(Text.translatable("planetskilltimer.config.exit"), button -> {
            mc.setScreen(parentScreen);
        }).dimensions(mc.getWindow().getScaledWidth() / 2 - 35, mc.getWindow().getScaledHeight() - 25, 70, 20).build();
        this.addDrawableChild(exitButton);

        XPosSlider = new Slider(getRegularX(), getRegularY()+(20+2)*5,150,20,Text.literal("X : "),0,1000,this.client.data.SkillTimerXpos){
            @Override
            protected void applyValue() {
                client.data.SkillTimerXpos = this.getValueInt();
                client.configManage.save();
            }
        };
        this.addDrawableChild(XPosSlider);
        YPosSlider = new Slider(getRegularX(), getRegularY()+(20+2)*6,150,20,Text.literal("Y : "),0,1000,this.client.data.SkillTimerYpos){
            @Override
            protected void applyValue() {
                client.data.SkillTimerYpos = this.getValueInt();
                client.configManage.save();
            }
        };
        this.addDrawableChild(YPosSlider);
    }
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        XPosSlider.render(context,mouseX,mouseY,delta);
        YPosSlider.render(context,mouseX,mouseY,delta);
        super.render(context, mouseX, mouseY, delta);
    }

    private void toggleSkilltimer(){
        if(client.data.toggleSkilltimer){
            toggleSkillTimerButton.setMessage(Text.translatable("planetskilltimer.config.skilltimer").append(
                    Text.translatable("planetskilltimer.config.disable").getWithStyle(Style.EMPTY.withFormatting(Formatting.RED).withBold(true)).get(0)));
            client.data.toggleSkilltimer = false;
            client.configManage.save();
        }
        else{
            toggleSkillTimerButton.setMessage(Text.translatable("planetskilltimer.config.skilltimer").append(
                    Text.translatable("planetskilltimer.config.enable").getWithStyle(Style.EMPTY.withFormatting(Formatting.GREEN).withBold(true)).get(0)));
            client.data.toggleSkilltimer = true ;
            client.configManage.save();
        }
    }

    private void toggleSkills(int skilltype){
        if(client.data.toggleSkills[skilltype]){
            toggleSkillsButton[skilltype].setMessage(Text.translatable("planetskilltimer.config."+SkillList[skilltype]).append(
                    Text.translatable("planetskilltimer.config.disable").getWithStyle(Style.EMPTY.withFormatting(Formatting.RED).withBold(true)).get(0)));
            client.data.toggleSkills[skilltype] = false;
            client.configManage.save();
        }
        else{
            toggleSkillsButton[skilltype].setMessage(Text.translatable("planetskilltimer.config."+SkillList[skilltype]).append(
                    Text.translatable("planetskilltimer.config.enable").getWithStyle(Style.EMPTY.withFormatting(Formatting.GREEN).withBold(true)).get(0)));
            client.data.toggleSkills[skilltype] = true ;
            client.configManage.save();
        }
    }

    int getRegularX() {
        return  mc.getWindow().getScaledWidth() / 2 - width / 2;
    }

    int getRegularY() {
        return mc.getWindow().getScaledHeight() / 2 - height / 2;
    }
}
