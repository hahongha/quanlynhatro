package com.utc.rental.rental.dto.roomType;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SearchRoomTypeDTO {
	public static final int MAX_20 = 20;
	public static final int MAX_200 = 200;
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	@Min(value = 0)
	private int page;

	@Min(value = 1)
	@Max(value = MAX_200)
	@Builder.Default
	private int size = MAX_200;

	@Builder.Default
	private String value = "%%";

	private List<OrderBy> orders;

	@Data
	@ToString
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class OrderBy {
		@Builder.Default
		private String order = ASC;// asc, desc
		private String property;
	}
	@Builder.Default
	Long minSize = Long.MIN_VALUE;
	@Builder.Default
	Long maxSize = Long.MAX_VALUE;
	@Builder.Default
	private String funiture ="%%";
}
