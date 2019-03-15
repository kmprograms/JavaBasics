package pl.kmprograms.ex4;

import org.mapstruct.Mapper;

@Mapper
public interface ProducerMapper {
    ProducerDto producerToProducerDto(Producer producer);
    Producer producerDtoToProducer(ProducerDto producerDto);
}
