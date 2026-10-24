package com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RBShortOutputRhymeRes {
    //Ref: https://www.poetryfoundation.org/education/glossary/rhyme
    //Ref: https://en.wikipedia.org/wiki/Rhyme

    private String search;
    private List<RBShortOutputRhymeResItem> rhymes;
    private Map<Integer,List<String>> allRhymes;

//    private List<String> end_rhymes;
//    private List<String> eye_rhymes;
//
//    private List<String> dactylic_rhymes; //aka fem
//
//    private List<String> single_rhymes; //aka fem
//    private List<String> stressed_rhymes;   //aka masc
//
//    private List<String> unstressed_rhymes; //aka fem
//    private List<String> double_rhymes;
//
//    private List<String> half_rhymes;
//    private List<String> identical_rhymes;
//
//    private List<String> pararhymes;

}
