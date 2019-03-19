import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class Action {

	String name;
	String contraints;
}
