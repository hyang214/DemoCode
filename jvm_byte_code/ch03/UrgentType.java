public enum UrgentType {

    NO((byte) 0, "非加急"),
    YES((byte) 1, "加急")
    ;

    private Byte code;
    private String name;

    private static Map<Byte, String> codeMap;
    static {
        codeMap = new HashMap<>();
        Arrays.stream(UrgentType.values()).forEach((x)->codeMap.put(x.code, x.name));
    }

    UrgentType(Byte code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 返回引用，防止修改
     * @return
     */
    public static Map<Byte, String> getCodeMap() {
        return new HashMap<>(codeMap);
    }
}
