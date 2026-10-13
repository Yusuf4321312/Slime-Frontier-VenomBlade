from pathlib import Path
import json
root = Path(__file__).resolve().parents[1]
catalog = json.loads((root / "src/main/resources/data/venomblade/catalog.json").read_text(encoding="utf-8"))
assert catalog.get("schema_version") == 1
for section in ("weapons", "armor"):
    for entry in catalog.get(section, []):
        assert entry.get("id") and entry.get("name_en") and entry.get("name_tr")
print("Catalog JSON valid.")
