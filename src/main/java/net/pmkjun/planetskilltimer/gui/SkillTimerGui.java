package net.pmkjun.planetskilltimer.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pmkjun.planetskilltimer.PlanetSkillTimerClient;
import net.pmkjun.planetskilltimer.file.Stat;
import net.pmkjun.planetskilltimer.util.SkillLevel;
import net.pmkjun.planetskilltimer.util.Timer;

public class SkillTimerGui {
    private MinecraftClient mc;
    private PlanetSkillTimerClient client;
    private TextRenderer font;

    private static final Identifier[] SKILL_ICONS = {
            new Identifier("planetskilltimer","skill_farming.png"),
            new Identifier("planetskilltimer","skill_felling.png"),
            new Identifier("planetskilltimer", "skill_mining.png"),
            new Identifier("planetskilltimer","skill_digging.png")
    };

    public SkillTimerGui(){
        this.mc = MinecraftClient.getInstance();
        this.client = PlanetSkillTimerClient.getInstance();
    }

    public void renderTick(DrawContext context, Timer timer){
        if(!this.client.data.toggleSkilltimer) return; //스킬타이머를 껏을때 실행x

        for(int skilltype = 0; skilltype < Stat.list.length ; skilltype++){
            render(context, SKILL_ICONS[skilltype], skilltype, timer.getDifference(client.data.lastSkillTime[skilltype]));
        }
    }

    private void render(DrawContext context,Identifier texture,int skilltype, long ms) {
        MatrixStack poseStack = context.getMatrices();
        double cooldown_ms;
        int activatetime;

        activatetime = SkillLevel.getActivateTime(skilltype,Stat.level[skilltype]);
        cooldown_ms = ms - activatetime;

        poseStack.push();
        poseStack.translate(2+18*skilltype,mc.getWindow().getScaledHeight()-18,0.0D);
        poseStack.scale(0.0625F, 0.0625F, 0.0625F);

        RenderSystem.setShaderTexture(0,texture);
        context.drawTexture(texture, 0, 0, 0, 0, 256, 256);
        poseStack.scale(16.0F, 16.0F, 16.0F);
        poseStack.pop();

        if(cooldown_ms < 0){
            //남은 지속시간
            System.out.println("남은 스킬 지속시간 : "+ ((activatetime-ms)/(double)1000) +"초");
            poseStack.push();
            poseStack.translate((2+18*skilltype+8), (mc.getWindow().getScaledHeight()-18 + 4), 0.0F);
            poseStack.scale(0.9090909F, 0.9090909F, 0.9090909F);
            context.drawCenteredTextWithShadow(this.mc.textRenderer, (Text)Text.literal(String.format("%.1f",((activatetime-ms)/(double)1000))), 0, 0, 16777215);
            poseStack.pop();


        }
        else if(cooldown_ms < 200000){
            System.out.println("남은 스킬 쿨타임 : "+(200-cooldown_ms/(double)1000)+"초");
            poseStack.push();
            poseStack.translate((2+18*skilltype+8), (mc.getWindow().getScaledHeight()-18 + 4), 0.0F);
            poseStack.scale(0.9090909F, 0.9090909F, 0.9090909F);
            context.drawCenteredTextWithShadow(this.mc.textRenderer, (Text)Text.literal(String.format("%.1f",(200-cooldown_ms/(double)1000))), 0, 0, 16777215);
            poseStack.pop();
        }

    }

}
