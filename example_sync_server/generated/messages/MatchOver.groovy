// automatically generated by the FlatBuffers compiler, do not modify

package messages;
import com.skillz.server.Message;
import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class MatchOver extends Message {
  public static void ValidateVersion() { Constants.FLATBUFFERS_1_12_0(); }
  public MatchOver get(ByteBuffer _bb) { return get(_bb, new MatchOver()); }
  public MatchOver get(ByteBuffer _bb, MatchOver obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public MatchOver __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public short opcode() { int o = __offset(4); return o != 0 ? bb.getShort(o + bb_pos) : 11; }
  public int playerScore() { int o = __offset(6); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int opponentScore() { int o = __offset(8); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public long abortingPlayer() { int o = __offset(10); return o != 0 ? bb.getLong(o + bb_pos) : 0L; }
  public long forfeitingPlayer() { int o = __offset(12); return o != 0 ? bb.getLong(o + bb_pos) : 0L; }

  public static int createMatchOver(FlatBufferBuilder builder,
      short opcode,
      int playerScore,
      int opponentScore,
      long abortingPlayer,
      long forfeitingPlayer) {
    builder.startTable(5);
    MatchOver.addForfeitingPlayer(builder, forfeitingPlayer);
    MatchOver.addAbortingPlayer(builder, abortingPlayer);
    MatchOver.addOpponentScore(builder, opponentScore);
    MatchOver.addPlayerScore(builder, playerScore);
    MatchOver.addOpcode(builder, opcode);
    return MatchOver.endMatchOver(builder);
  }

  public static void startMatchOver(FlatBufferBuilder builder) { builder.startTable(5); }
  public static void addOpcode(FlatBufferBuilder builder, short opcode) { builder.addShort(0, opcode, 11); }
  public static void addPlayerScore(FlatBufferBuilder builder, int playerScore) { builder.addInt(1, playerScore, 0); }
  public static void addOpponentScore(FlatBufferBuilder builder, int opponentScore) { builder.addInt(2, opponentScore, 0); }
  public static void addAbortingPlayer(FlatBufferBuilder builder, long abortingPlayer) { builder.addLong(3, abortingPlayer, 0L); }
  public static void addForfeitingPlayer(FlatBufferBuilder builder, long forfeitingPlayer) { builder.addLong(4, forfeitingPlayer, 0L); }
  public static int endMatchOver(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public MatchOver get(int j) { return get(new MatchOver(), j); }
    public MatchOver get(MatchOver obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

