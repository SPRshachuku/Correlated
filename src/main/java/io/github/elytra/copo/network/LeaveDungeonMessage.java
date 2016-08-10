package io.github.elytra.copo.network;

import io.github.elytra.copo.CoPo;
import io.github.elytra.copo.world.LimboProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LeaveDungeonMessage implements IMessage, IMessageHandler<LeaveDungeonMessage, IMessage> {
	@Override public void fromBytes(ByteBuf buf) { }
	@Override public void toBytes(ByteBuf buf) { }

	@Override
	public IMessage onMessage(LeaveDungeonMessage message, MessageContext ctx) {
		ctx.getServerHandler().playerEntity.mcServer.addScheduledTask(() -> {
			int dim = CoPo.limboDimId;
			WorldServer world = ctx.getServerHandler().playerEntity.mcServer.worldServerForDimension(dim);
			WorldProvider provider = world.provider;
			if (provider instanceof LimboProvider) {
				((LimboProvider)provider).addLeavingPlayer(ctx.getServerHandler().playerEntity.getGameProfile().getId());
			}
		});
		return null;
	}

}