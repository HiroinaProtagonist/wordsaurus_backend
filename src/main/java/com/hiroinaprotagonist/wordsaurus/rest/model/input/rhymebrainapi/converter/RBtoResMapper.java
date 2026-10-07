package com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.converter;

import com.hiroinaprotagonist.wordsaurus.rest.model.input.rhymebrainapi.response.RBRhymeResItem;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RhymeBrainOutputResItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RBtoResMapper {

    RBtoResMapper MAPPER_INSTANCE = Mappers.getMapper(RBtoResMapper.class);

    @Mapping(source = "word", target = "word")
    @Mapping(source = "freq", target = "freq")
    @Mapping(source = "score", target = "score")
    @Mapping(source = "flags", target = "flags")
    @Mapping(source = "isOffensive", target = "isOffensive")
    @Mapping(source = "isDictionaryWord", target = "isDictionaryWord")
    @Mapping(source = "isKnownPronunciation", target = "isKnownPronunciation")
    public RhymeBrainOutputResItem rbToResMapper(RBRhymeResItem input);
}
