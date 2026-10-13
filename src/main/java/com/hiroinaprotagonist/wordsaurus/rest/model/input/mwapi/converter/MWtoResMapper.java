package com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.converter;

import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.MWThesaurusWord;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWOutputThesResWord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MWtoResMapper {

    MWtoResMapper MAPPER_INSTANCE = Mappers.getMapper(MWtoResMapper.class);

    @Mapping(source = "meta", target = "meta")
    @Mapping(source = "meta.id", target = "metaId")
    @Mapping(source = "meta.uuid", target = "metaUuid")
    @Mapping(source = "meta.src", target = "metaSrc")
    @Mapping(source = "meta.section", target = "metaSection")
    @Mapping(source = "meta.target", target = "mwTarget")
    @Mapping(source = "fl", target = "partOfSpeech")
    @Mapping(source = "shortDef", target = "shortDef")

    @Mapping(source = "meta.stems", target = "stems")
    @Mapping(source = "meta.syns", target = "synonyms")
    @Mapping(source = "meta.ants", target = "antonyms")

    @Mapping(target="rhyming", ignore = true)

    @Mapping(source = "meta.offensive", target = "offensive")
    @Mapping(source = "hwi", target = "hwi")
    public MWOutputThesResWord mwToResMapper(MWThesaurusWord input);
}
