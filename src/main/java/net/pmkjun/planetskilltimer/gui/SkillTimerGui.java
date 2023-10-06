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
import net.pmkjun.planetskilltimer.util.Timer;

public class SkillTimerGui extends DrawContext {
    private MinecraftClient mc;
    private PlanetSkillTimerClient client;
    private TextRenderer font;

    private static final Identifier[] SKILL_ICONS = {
            new Identifier("planetskilltimer","Skill_Farming"),
            new Identifier("planetskilltimer","Skill_Felling"),
            new Identifier("planetskilltimer", "Skill_Mineing.png"),
            new Identifier("planetskilltimer","SKill_Digging")
    };

    public SkillTimerGui(){
        super(MinecraftClient.getInstance(), VertexConsumerProvider.immediate(new BufferBuilder(10)));
        this.mc = MinecraftClient.getInstance();
        this.client = PlanetSkillTimerClient.getInstance();
    }

    public void renderTick(DrawContext context, Timer timer){

    }

    private void render(DrawContext context,Identifier texture, int second) {

    }

}
