package top.kthirty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import top.kthirty.constant.UserIndexConstant;

/**
 * 用户搜索引擎实体
 */
@Document(indexName = UserIndexConstant.INDEX_NAME,type = UserIndexConstant.TYPE,shards = 1,replicas = 0)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserCO {
    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Integer)
    private Integer age;
}
