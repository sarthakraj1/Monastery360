package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.monastery360.tourism.DataBinderMapperImpl());
  }
}
