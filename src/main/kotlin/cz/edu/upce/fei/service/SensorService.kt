package cz.edu.upce.fei.service

import cz.edu.upce.fei.dto.SensorDto
import cz.edu.upce.fei.repository.SensorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SensorService(
    @Autowired val sensorRepository: SensorRepository,
    @Autowired val deviceService: DeviceService
) {

    fun listFiltered(deviceId: Long): Iterable<SensorDto> {
        return sensorRepository.findByDeviceId(deviceId).map { it.toDto() }
    }

    fun save(sensorDto: SensorDto): SensorDto {
        return sensorRepository.save(sensorDto.toModel { deviceService.findById(sensorDto.deviceId) }).toDto()
    }
}