package tournament;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class BracketImpl_Ng<P> extends BracketAbstract<P> {
    public BracketImpl_Ng(List<P> participantMatchups) {
        super(participantMatchups);
    }

	/*	Precondition(s):
							None
	*/

	/*	Postcondition(s):
							getGroupings(rv).size() == 1
							2^rv == getGroupings(0).size()
	*/

	/*	Example Calls:
	Brack<Letters> bracket = new BracketImpl_Ng(Array.asList(A)
			bracket.getMaxLevel() => 0

	Brack<Letters> bracket = new BracketImpl_Ng(Array.asList(A,a)
			bracket.getMaxLevel() => 1

	Brack<Letters> bracket = new BracketImpl_Ng(Array.asList(A,a,B,b))
			bracket.getMaxLevel() => 2

	Brack<Letters> bracket = new BracketImpl_Ng(Array.asList(A,a,B,b,C,c,D,d))
			bracket.getMaxLevel() => 3

	Brack<Letters> bracket = new BracketImpl_Ng(Array.asList(A,a,B,b,C,c,D,d,E,e,F,f,G,g,H,h))
			bracket.getMaxLevel() => 4
	*/

	/*	Algorithm:
			Time:	O(c)
			Space:	O(n)

			There exists some relationship between the attributes of our bracket. The attribute this particular
			implementation exploits are the number of nodes that exist in our tree. And due to the fact that are tree
			is in fact a balanced binary tree, it probably has something to do with a power of 2. With that said,
			there's nothing alogorithmic about this implementation outside of some alegebra with log base 2
	*/

    public int getMaxLevel() {
        int numNodes = getNumNodes();
        int maxLevel = log_2(numNodes + OFFSET_BY_ONE) - OFFSET_BY_ONE;
        return maxLevel;
    }

	/*	Precondition(s): TODO: EXECUTABLE PRECONDITIONS
							0 <= level <= greMaxLevel()
	*/

	/*	Postcondition(s):
							rv.size equal to 2 power num circles in level
							rv at some level shoulc contain all participants
	*/

	/*	Example Calls:
							Considering placement in bracket as no bearing on
							getGroupings, we will assume that brack has just
							been instantiated
	Brack<Letters> bracket = new BracketImpl_Ng(Array.asList(A)
			bracket.getGroupings(0) =>
			{{A}}

	Brack<Letters> bracket = new BracketImpl_Ng(Array.asList(A,a)
			bracket.getGroupings(0) =>
			{{A}, {a}}
			bracket.getGroupings(1) =>
			{{A, a}}

	Brack<Letters> bracket = new BracketImpl_Ng(Array.asList(A,a,B,b))
			bracket.getGroupings(0) =>
			{{A}, {a}, {B}, {b}}
			bracket.getGroupings(1) =>
			{{A, a}, {B, b}}
			bracket.getGroupings(2) =>
			{{A, a, B, b}}

	Brack<Letters> bracket = new BracketImpl_Ng(Array.asList(A,a,B,b,C,c,D,d))
			bracket.getGroupings(0) =>
			{{A}, {a}, {B}, {b}, {C}, {c}, {D}, {d}}
			bracket.getGroupings(1) =>
			{{A, a}, {B, b}, {C, c}, {D, d}}
			bracket.getGroupings(2) =>
			{{A, a, B, b}, {C, c, D, d}}
			brack.getGroupings(3) =>
			{{A, a, B, b, C, c, D, d}}

	Brack<Letters> bracket = new BracketImpl_Ng(Array.asList(A,a,B,b,C,c,D,d,E,e,F,f,G,g,H,h))
			bracket.getGroupings(0) =>
			{{A}, {a}, {B}, {b}, {C}, {c}, {D}, {d}, {E}, {e}, {F}, {f}, {G}, {g}, {H}, {h}}
			bracket.getGroupings(1) =>
			{{A, a}, {B, b}, {C, c}, {D, d}, {E, e}, {F, f}, {G, g}, {H, h}}
			bracket.getGroupings(2) =>
			{{A, a, B, b}, {C, c, D, d}, {E, e, F, f}, {G, g, H, h}}
			brack.getGroupings(3) =>
			{{A, a, B, b, C, c, D, d}, {E, e, F, f, G, g, H, h}}
			bracket.getGroupings(4) =>
			{A, a, B, b, C, c, D, d, E, e, F, f, G, g, H, h}
	*/

    /*	Algorithm:
            Time:	O(n^2)
            Space:	O()

            There are two key attributes that we know about the return value; those being the size of the return value
            and the sizes of the inner Sets, which by the way should all be the same size. These are both derivable,
            with some steps in the middle, attributes from just incoming level parameter alone! Seperate but important,
            we want to know what the first index of level 0 is, because all we care about what participants came into
            the bracket. Bracket mutations have no bearing on this method's rv.

            In addition, because our predictions is a List, order does in fact matter in that every pairwises of powers
            of 2 have meaning. And because we know what the inner set sizes are suppose to be, we can now send sublists
            of those sizes, and because order matters in a list they're already paired properly! And we know where
            those chunks should start and end in the predictions list because all of the inner sets are the same size,
            so it's means we can have linear steppings instead of one that might have to change on certain conditions.
            In other words, the start of chunk 2 could be interpretted as the end of chunk 1. How this is manifested
            will vary, but in this particular implementation we will utilise which chunk (zero based) we are on and
            maintain the start index of level 0 throughout the looping, and our end is just start plus the group size.
    */
    public Set<Set<P>> getGroupings(int level) {
        assert 0 <= level : "Invalid send! : <" + level + "> " + "You cannot send a negative level!";
        assert level <= getMaxLevel() : "Invalid send! : <" + level + "> " +
                                        "You cannot send a level greater than the possible amount of levels!";

        Set<Set<P>> rv = new HashSet<>();

        int inverseLevel = getInverseLevel(level);
        int rvSize = (int) Math.pow(2, inverseLevel);
        int groupingSize = (int) Math.pow(2, level);
        int itr = getStartIndexOfLevelN(0);

        for (int group = 0; group < rvSize; group++) {
            int start = itr + group * groupingSize;
            int end = start + groupingSize;
            Set<P> grouping = new HashSet<>(predictions.subList(start, end));
            rv.add(grouping);
        }
        return rv;
    }

	/*	Precondition(s): TODO: EXECUTABLE PRECONDITIONS AND FINISH DOCUMENTATION
							grouping != null
							predictions.containsALl(grouping);
							getGroupings(log_2(grouping.size())).contains(grouping);
	*/

	/*	Postcondition(s):
							rv != null
							rv.size() > 0
							grouping.containsALl(rv)
	*/

	/*	Example Calls:
	Brack<Letters> bracket = new BracketImpl_Ng(Array.asList(A,a,B,b,C,c,D,d,E,e,F,f,G,g,H,h))

		bracket.setWinCount(A, 2)
		bracket.setWinCount(h, 2)
		bracket.setWinCount(E, 4)
		bracket.setWinCount(c, 1)
		bracket.setWinCount(D, 4)
		bracket.setWinCount(C, 2)

			           *					L4
		       *---------------E			L3
		   *-------c       E-------h		L2
		 A---*   c---D   E---*   *---h		L1
		A-a B-b C-c D-d E-e F-f G-g H-h		L0

		bracket.getViableParticipants(A, a)												=>	{A}
		bracket.getViableParticipants(B, b)												=>	{B, b}
		bracket.getViableParticipants(A, a, B, b)										=>	(A, B, b)
		bracket.getViableParticipants(E, e, F, f)										=>	{E}
		bracket.getViableParticipants(A, a, B, b, C, c, D, d)							=>	{A, B, b, c}
		bracket.getViableParticipants(E, e, F, f, G, g, H, h)							=>	{E}
		bracket.getViableParticipants(A, a, B, b, C, c, D, d, E, e, F, f, G, g, H, h)	=> (A, B, b, c, E)
	*/

    /*	Algorithm:
            Time:	O(n^2)
            Space:	O(n)

            The approach taken is almost like a whitelist, but it is also very much a naive floodfill approach without
            much otimisation. It will go through every participant in the group, check their parent node. Who the
            parent mode is will allow us to tell whether the current node has been 'eliminated' or not yet.
    */
    public Set<P> getViableParticipants(Set<P> grouping) {
        assert grouping != NO_PARTICIPANT : "Invalid send! : <null> You cannot send a null value!";
        assert !grouping.contains(NO_PARTICIPANT) :
                "Invalid send! : <" + grouping + "> " + "You cannot send a grouping that contains a null!";
        assert predictions.containsAll(grouping) : "Invalid send! : <" + grouping + "> " +
                                                   "You cannot send a grouping with participant(s) that aren't in the" +
                                                   " bracket!";
        assert getGroupings(log_2(grouping.size())).contains(grouping) : " Invalid send! : <" + grouping + "> " +
                                                                         "You cannot send a grouping that isn't a " +
                                                                         "valid grouping in this bracket!";

        Set<P> rv = new HashSet<>(grouping);

        //For each participant, check if they won or lost.
        for (P participant : grouping) {
            boolean eliminated = false;
            int highestGroupLevel = log_2(grouping.size());
            int level = 0;
            int index = getParentIndex(getIndexOfParticipantOnLevelN(participant, 0));
            while (level < highestGroupLevel && !eliminated) {
                P curNode = predictions.get(index);
                eliminated = curNode != participant && curNode != NO_PARTICIPANT;
                if (eliminated) {
                    rv.remove(participant);
                }
                index = getParentIndex(index);
                level++;
            }
        }

        return rv;
    }

	/*	Precondition(s): TODO: EXECUTABLE PRECONDITIONS -> DONE FOR AS MANY AS THERE WERE THOUGHT OF
							participant != null
							predictions.contains(participant)
							0 <= winCount <= getMaxLevel()
	*/

	/*	Postcondition(s):
							(0 < level <= winCount())) ==> getViableParticipants(getGrouping(level)).contains
							(participant)

							(getViableParticipants(getGrouping(exactWinCount)).contains(t) &&
							(winCount < level <= getMaxLevel())) ==> !getViableParticipants(getGrouping(level))
							.contains(t)
	*/

	/*	Example Calls:
	Brack<Letters> bracket = new BracketImpl_Ng(Array.asList(A,a,B,b,C,c,D,d,E,e,F,f,G,g,H,h))
					   *					L4
		       *---------------*			L3
		   *-------*       *-------*		L2
		 *---*   *---*   *---*   *---*		L1
		A-a B-b C-c D-d E-e F-f G-g H-h		L0

		bracket.setWinCount(A, 3)
		bracket.setWinCount(h, 2)
		bracket.setWinCount(E, 4)
		bracket.setWinCount(c, 1)
				       E					L4
		       A---------------E			L3
		   A-------*       E-------h		L2
		 A---*   c---*   E---*   *---h		L1
		A-a B-b C-c D-d E-e F-f G-g H-h		L0

		bracket.setWinCount(D, 4)
				       D					L4
		       D---------------E			L3
		   A-------D       E-------h		L2
		 A---*   c---D   E---*   *---h		L1
		A-a B-b C-c D-d E-e F-f G-g H-h		L0

		bracket.setWinCount(C, 2)
				       *					L4
		       *---------------E			L3
		   A-------c       E-------h		L2
		 A---*   c---D   E---*   *---h		L1
		A-a B-b C-c D-d E-e F-f G-g H-h		L0


	*/

    /*	Algorithm:
            Time:	O(n)
            Space:	O(n)

            First we need to find where to start. This would be where the participant lives at level zero
            and that's due to the bottom up traversing nature of a bracket like structure.
            The node we are mutating will be the parent of the current node and we will do this for as many times and
            there are winCount.
            NOTE: WinCount == n,  can be interpreted as traverse from level == 0 until level == n
            In addition, we will keep track of the participant we just replaced throughout this process. This will
            make our job for the next step much easier.

            Now we also need to check if the rest of the parent nodes above level == winCount along its path to the
            root need to be null or not. We will now iterate from where we left off until we hit the highest level.
            Because we kept track of the was replaced at the level == winCount by the incoming parameter, we can make a
            check at each step asking if the parent node was the replaced participant. If it is, then we set it to null.
            else break condition. What this effectively does is make sure that we aren't affecting the mutations caused
            by a different branch on that particular parent node. Another escape condition is if the parent node is
            null. This is because a null value as a parent node tells us that no one in the grouping of that parent at
            has a prediction past the current node.
    */
    public void setWinCount(P participant, int winCount) {
        assert participant != NO_PARTICIPANT : "Invalid send! : <null> You cannot send a null value!";
        assert predictions.contains(participant) : "Invalid send! : <" + participant + "> " +
                                                   "You cannot ask about a participant who isn't in the bracket!";
        assert 0 <= winCount : "Invalid send! : <" + winCount + "> " + "You cannot have a negative winCount!";
        assert winCount <= getMaxLevel() :
                "Invalid send! : <" + winCount + "> " + "You cannot have more wins than possible!";

        int parentIndex = getIndexOfParticipantOnLevelN(participant, 0);
        int levelsLeftToCover = getMaxLevel() - winCount;
        P replacedParticipant = NO_PARTICIPANT;

        // Replace with participant until winCount
        for (int win = 0; win < winCount; win++) {
            parentIndex = getParentIndex(parentIndex);
            replacedParticipant = predictions.get(parentIndex);
            predictions.set(parentIndex, participant);
        }

        // Escpace conditions
        int level = 0;
        boolean wasReplaced = true;
        boolean isNull = false;
        while (level < levelsLeftToCover && wasReplaced && !isNull) {
            parentIndex = getParentIndex(parentIndex);
            P parent = predictions.get(parentIndex);
            wasReplaced = parent == replacedParticipant;
            isNull = parent == NO_PARTICIPANT;
            if (wasReplaced && !isNull) {
                predictions.set(parentIndex, NO_PARTICIPANT);
                level++;
            }
        }
    }

    private final P NO_PARTICIPANT = null;
    private final int OFFSET_BY_ONE = 1;

    private int getNumNodes() {
        return predictions.size();
    }

    private int log_2(int x) {
        return (int) (Math.log(x) / Math.log(2));
    }

    private int getInverseLevel(int level) {
        assert 0 <= level;
        assert level <= getMaxLevel();

        return getMaxLevel() - level;
    }

    private int getStartIndexOfLevelN(int n) {
        assert 0 <= n;
        assert n <= getMaxLevel();

        int inverseLevel = getInverseLevel(n);
        return (int) Math.pow(2, inverseLevel) - OFFSET_BY_ONE;
    }

    private int getEndIndexOfLevelN(int n) {
        assert 0 <= n;
        assert n <= getMaxLevel();

        int startIndex = getStartIndexOfLevelN(n);
        return 2 * startIndex;
    }

    private int getIndexOfParticipantOnLevelN(P participant, int n) {
        assert 0 <= n;
        assert n <= getMaxLevel();

        int itr = getStartIndexOfLevelN(n);
        int end = getEndIndexOfLevelN(n);
        int rv = itr;
        boolean found = false;
        while (itr < end && !found) {
            found = predictions.get(itr) == participant;
            if (found) {
                rv = itr;
            }
            itr++;
        }
        return rv;
    }

    private int getParentIndex(int childIndex) {
        assert childIndex >= 0;
        assert childIndex < getNumNodes();

        int numerator = childIndex % 2 == 0 ? (childIndex - 2) : (childIndex - 1);
        return numerator / 2;
    }

    private final String WHITE_SPACE_PADDING = " ";
    private final String DASH_PADDING = "-";
    private final String SPLAT = "*";

    private String levelBasedToString() {
        StringBuilder sb = new StringBuilder();

        int maxLevel = getMaxLevel();
        for (int i = maxLevel; i >= 0; i--) {
            StringBuilder inverseLevel_i = new StringBuilder();
            StringBuilder leftSidePadding = new StringBuilder();
            StringBuilder partPaddingSpace = new StringBuilder();
            StringBuilder partPaddingMatch = new StringBuilder();


            for (int ws = 0; ws < (int) Math.pow(2, i); ws++) {
                leftSidePadding.append(WHITE_SPACE_PADDING);
            }
            for (int ws = 0; ws < (int) Math.pow(2, i + OFFSET_BY_ONE) - OFFSET_BY_ONE; ws++) {
                partPaddingSpace.append(WHITE_SPACE_PADDING);
            }
            for (int ws = 0; ws < (int) Math.pow(2, i + OFFSET_BY_ONE) - OFFSET_BY_ONE; ws++) {
                partPaddingMatch.append(DASH_PADDING);
            }
            int start = getStartIndexOfLevelN(i);
            int end = getEndIndexOfLevelN(i);

            inverseLevel_i.append(leftSidePadding);
            for (int j = start; j <= end; j++) {
                P participant = predictions.get(j);
                if (participant == NO_PARTICIPANT) {
                    inverseLevel_i.append(SPLAT);
                } else {
                    inverseLevel_i.append(participant);
                }
                if (j != end) {
                    if (j % 2 == 0) {
                        inverseLevel_i.append(partPaddingSpace.toString());
                    } else {
                        inverseLevel_i.append(partPaddingMatch.toString());
                    }
                }
            }
            // If i want a bit of extra spacing
            // sb.append("\n");
            sb.append(inverseLevel_i + "\n");


        }
        return "\n" + sb.toString();
    }

    @Override
    public String toString() {
        return levelBasedToString();
        // return leftJustifiedToString();
    }
}
