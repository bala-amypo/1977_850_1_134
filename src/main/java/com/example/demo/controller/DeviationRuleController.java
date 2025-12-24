@RestController
@RequestMapping("/deviation-rules")
public class DeviationRuleController {

    private final DeviationRuleService deviationRuleService;

    public DeviationRuleController(DeviationRuleService deviationRuleService) {
        this.deviationRuleService = deviationRuleService;
    }

    @PostMapping
    public DeviationRule create(@RequestBody DeviationRule rule) {
        return deviationRuleService.createRule(rule);
    }

    @GetMapping
    public List<DeviationRule> getAll() {
        return deviationRuleService.getAllRules();
    }
}
