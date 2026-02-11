import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoWinningInfoTest {

    @Test
    @DisplayName("3개 일치 + 보너스 여부와 상관없이 MATCH_3이다")
    void match3_ignoreBonus() {
        assertEquals(
                LottoWinningInfo.MATCH_3,
                LottoWinningInfo.getLottoWinningInfo(3, false)
        );

        assertEquals(
                LottoWinningInfo.MATCH_3,
                LottoWinningInfo.getLottoWinningInfo(3, true)
        );
    }

    @Test
    @DisplayName("4개 일치 + 보너스 여부와 상관없이 MATCH_4이다")
    void match4_ignoreBonus() {
        assertEquals(
                LottoWinningInfo.MATCH_4,
                LottoWinningInfo.getLottoWinningInfo(4, false)
        );

        assertEquals(
                LottoWinningInfo.MATCH_4,
                LottoWinningInfo.getLottoWinningInfo(4, true)
        );
    }

    @Test
    @DisplayName("5개 일치 + 보너스가 없으면 MATCH_5이다")
    void match5_withoutBonus() {
        assertEquals(
                LottoWinningInfo.MATCH_5,
                LottoWinningInfo.getLottoWinningInfo(5, false)
        );
    }

    @Test
    @DisplayName("5개 일치 + 보너스가 있으면 MATCH_5_BONUS이다")
    void match5_withBonus() {
        assertEquals(
                LottoWinningInfo.MATCH_5_BONUS,
                LottoWinningInfo.getLottoWinningInfo(5, true)
        );
    }

    @Test
    @DisplayName("6개 일치면 MATCH_6이다")
    void match6() {
        assertEquals(
                LottoWinningInfo.MATCH_6,
                LottoWinningInfo.getLottoWinningInfo(6, false)
        );
    }



    @Test
    @DisplayName("0개, 1개, 2개 일치는 낙첨이므로 NONE을 반환한다")
    void noPrize_returnsNone() {
        assertEquals(LottoWinningInfo.NONE, LottoWinningInfo.getLottoWinningInfo(0, false));
        assertEquals(LottoWinningInfo.NONE, LottoWinningInfo.getLottoWinningInfo(1, false));
        assertEquals(LottoWinningInfo.NONE, LottoWinningInfo.getLottoWinningInfo(2, false));
    }
}