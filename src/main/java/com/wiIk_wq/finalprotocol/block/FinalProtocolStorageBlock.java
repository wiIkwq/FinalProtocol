package com.wiIk_wq.finalprotocol.block;

import com.wiIk_wq.finalprotocol.menu.FinalProtocolStorageMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import io.netty.buffer.Unpooled;
import org.jetbrains.annotations.Nullable;

public class FinalProtocolStorageBlock extends Block implements EntityBlock {

    public FinalProtocolStorageBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.FINAL_PROTOCOL_STORAGE_ENTITY.get().create(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            MenuProvider provider = new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return Component.literal("Final Protocol Storage");
                }

                @Nullable
                @Override
                public AbstractContainerMenu createMenu(int id, net.minecraft.world.entity.player.Inventory inv, Player p) {
                    return new FinalProtocolStorageMenu(id, inv, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
                }
            };
            System.out.println("[FinalProtocol] Right-click detected on block!");
            NetworkHooks.openScreen(serverPlayer, provider, pos);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}