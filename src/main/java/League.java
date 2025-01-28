import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class League {

    private String leagueName;
    private List<Member> memberList = new ArrayList<>();
    private Map<String, Integer> flamesTable = new HashMap<>();
    private Map<String, Integer> poopTable = new HashMap<>();
    TableSorter tableSorter = new TableSorter();

    public League(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void addMember(Member member) {
        memberList.add(member);
    }

    public Member getMember(String playerName) {
        return memberList.stream()
                .filter(member -> member.getPlayerName().equals(playerName))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Integer> getFlamesTable() {
        memberList.forEach(member -> flamesTable.put(member.getPlayerName(), member.getTotalFlames()));

        return tableSorter.sortMapByValueDescending(flamesTable);
    }

    public Map<String, Integer> getPoopTable() {
        memberList.forEach(member -> poopTable.put(member.getPlayerName(), member.getTotalPoop()));

        return tableSorter.sortMapByValueDescending(poopTable);
    }
}
