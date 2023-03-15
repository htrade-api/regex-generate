package it.units.inginf.male.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This object represents a range of contigous row indexes
 * @author nvhuy9527
 */
public class Range implements Comparable<Range>{
    private int startIndex;
    private int endIndex;

    public Range(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    /**
     * The index of the first row (inclusive) of the range
     * @return the index
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * The index of the last row (inclusive) of the range
     * @return the index
     */
    public int getStartIndex() {
        return startIndex;
    }


    public int getLength(){
        return endIndex-startIndex+1;
    }
    
    /**
     * Merges ranges in order to obtain a more compact representation. This is intended to work with no-overlapping Ranges.
     * Behavior with overlapping ranges is undefined.
     * @param ranges
     * @return
     */
    static public List<Range> compactRanges(List<Range> ranges){
        List<Range> newRanges = new LinkedList<>();
        if(ranges.isEmpty()){
            return newRanges;
        }
        
        Collections.sort(ranges);
        Range prevRange = new Range(ranges.get(0).startIndex, ranges.get(0).endIndex);
        for (int i = 1; i < ranges.size(); i++) {
            Range currentRange = ranges.get(i);
            if(currentRange.startIndex == prevRange.endIndex+1){
                //merge
                prevRange.endIndex = currentRange.endIndex;
            } else {
                newRanges.add(prevRange);
                prevRange = new Range(currentRange.startIndex, currentRange.endIndex);
            }
        }
        newRanges.add(prevRange);
        return newRanges;
    }

    @Override
    public int compareTo(Range o) {
       return (this.startIndex - o.startIndex);
    }
    
    
}
