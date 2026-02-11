import java.util.Arrays;

public enum LottoWinningInfo {
    MATCH_3(3, false, 5_000, "3개 일치 "),
    MATCH_4(4, false, 50_000, "4개 일치 "),
    MATCH_5(5, false, 1_500_000, "5개 일치 "),
    MATCH_5_BONUS(5, true, 30_000_000, "5개 일치, 보너스 볼 일치"),
    MATCH_6(6, false, 2_000_000_000L, "6개 일치 "),
    NONE(-1, false, 0, "일치 하지 않음");

    private final int matchCount;
    private final boolean bonusMatch;
    private final long prize;
    private final String description;

    LottoWinningInfo(int matchCount, boolean bonusMatch, long prize, String description) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.prize = prize;
        this.description = description;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }

    public long getPrize() {
        return prize;
    }

    public String getDescription() {
        return description;
    }


    public static LottoWinningInfo getLottoWinningInfo(int matchCount, boolean bonusMatch) {

        // 보너스는 5개 일치일 때만 의미 있음
        if (matchCount == 5 && bonusMatch) {
            return MATCH_5_BONUS;
        }

        return Arrays.stream(values())
                .filter(info -> info.matchCount == matchCount)
                .filter(info -> !info.bonusMatch) // 보너스 미적용 케이스
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "LottoWinningInfo{" +
                "matchCount=" + matchCount +
                ", bonusMatch=" + bonusMatch +
                ", prize=" + prize +
                ", description='" + description + '\'' +
                '}';
    }
}
