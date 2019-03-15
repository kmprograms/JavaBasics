package pl.kmprograms.ex5;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface WorkerMapper {

    @Mappings({
            @Mapping(target = "hireDate", source = "hireDate", dateFormat = "yyyy-MM-dd"),
            @Mapping(target = "companyName", source = "company.name")
    })
    WorkerDto workerToWorkerDto(Worker worker);

    @Mappings({
            @Mapping(target = "hireDate", source = "hireDate", dateFormat = "yyyy-MM-dd"),
            @Mapping(target = "company.name", source = "companyName")
    })
    Worker workerDtoToWorker(WorkerDto workerDto);
}
