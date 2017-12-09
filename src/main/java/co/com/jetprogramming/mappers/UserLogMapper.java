package co.com.jetprogramming.mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import co.com.jetprogramming.model.UserLog;
import co.com.jetprogramming.model.pojos.UserLogDto;

@Service
public class UserLogMapper implements EntityMapper<UserLog, UserLogDto> {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserLog mapEntity(UserLogDto dto) {
		UserLog map = new UserLog();
		String dateLog = dto.getDateLog();
		Assert.notNull(dateLog, "date log cant be null!");
		map.setDateLog(LocalDate.parse(dateLog));
		map.setDatetimeLog(LocalDateTime.now(ZoneId.of("America/Bogota")));
		return map;
	}

	@Override
	public UserLogDto mapDto(UserLog entity) {
		UserLogDto map = modelMapper.map(entity, UserLogDto.class);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		map.setDateTimeLog(entity.getDatetimeLog().format(formatter));
		return map;
	}

}
