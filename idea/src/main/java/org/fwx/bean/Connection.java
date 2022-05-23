package org.fwx.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * [使用 GsonOrXmlFormat 插件，对 xml 数据生成的实体类。
 * 1.首先新建你要的JavaBean实体类，类名根据你的需求来定，无任何内容，就一个空类；
 * 2.然后直接使用快捷键ALT+S或则对着类名右击—>Generate—>点击GsonOrXmlFormat；
 * 3.把你要解析的 xml 数据直接粘过来，点击就OK就行了。]
 *
 * @author : [fwx]
 * @version : [v1.0]
 * @createTime : [2022/5/6 9:08]
 */
public class Connection {
    @JsonProperty("Configuration")
    private ConfigurationDTO Configuration;

    public ConfigurationDTO getConfiguration() {
        return Configuration;
    }

    public void setConfiguration(ConfigurationDTO Configuration) {
        this.Configuration = Configuration;
    }

    public static class ConfigurationDTO {
        @JsonProperty("Name")
        private String Name;
        @JsonProperty("Description")
        private String Description;
        @JsonProperty("Module")
        private List<ModuleDTO> Module;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public List<ModuleDTO> getModule() {
            return Module;
        }

        public void setModule(List<ModuleDTO> Module) {
            this.Module = Module;
        }

        public static class ModuleDTO {
            @JsonProperty("Name")
            private String Name;
            @JsonProperty("Type")
            private String Type;
            @JsonProperty("Variable")
            private String Variable;
            @JsonProperty("Description")
            private String Description;
            @JsonProperty("Item")
            private List<ItemDTO> Item;

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getType() {
                return Type;
            }

            public void setType(String Type) {
                this.Type = Type;
            }

            public String getVariable() {
                return Variable;
            }

            public void setVariable(String Variable) {
                this.Variable = Variable;
            }

            public String getDescription() {
                return Description;
            }

            public void setDescription(String Description) {
                this.Description = Description;
            }

            public List<ItemDTO> getItem() {
                return Item;
            }

            public void setItem(List<ItemDTO> Item) {
                this.Item = Item;
            }

            public static class ItemDTO {
                @JsonProperty("Name")
                private String Name;
                @JsonProperty("Value")
                private String Value;
                @JsonProperty("Description")
                private String Description;

                public String getName() {
                    return Name;
                }

                public void setName(String Name) {
                    this.Name = Name;
                }

                public String getValue() {
                    return Value;
                }

                public void setValue(String Value) {
                    this.Value = Value;
                }

                public String getDescription() {
                    return Description;
                }

                public void setDescription(String Description) {
                    this.Description = Description;
                }
            }
        }
    }
}
