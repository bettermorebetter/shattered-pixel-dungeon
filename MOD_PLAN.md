# 모드 개발 로드맵 (MOD_PLAN.md)

이 문서는 모드의 **전체 기획·결정사항·진행상황**을 추적한다.
설계 철학(비전·3원칙)은 [`CLAUDE.md`](CLAUDE.md) 참조. 이 문서는 그 원칙을 실제 작업으로 푼 것이다.

> **다음 세션 시작 시:** 아래 "진행 상황" 표를 먼저 보고, 미완료된 가장 위 항목부터 이어서 진행할 것.
> 코드 위치는 맨 아래 "코드 맵" 참조 (재탐색 불필요).

---

## ✅ 확정된 설계 결정 (논의 완료, 2026-06-03)

원칙과 충돌할 소지가 있어 사용자(게임 디렉터)와 논의해 확정한 사항들.

1. **스탯 시스템:** 정확성/회피를 스탯창에 **표시만** 한다. 스탯 *직접 분배* 시스템은 **보류**.
   - 이유: 분배(특히 힘)를 레벨업에 묶으면 "탐험으로만 강해진다"는 핵심 자물쇠가 풀려 탐험 원칙과 충돌.
   - 추후 분배를 도입한다면, 힘(STR)은 물약 전용으로 유지하고 정확성/회피/체력만 분배하는 방향으로 재논의.

2. **강화 경제:** **골드 강화 + 상한선** 방식. (무제한 강화는 채택 안 함 → "쉬워짐" 방지)
   - 어느 시점에나 골드를 지불해 장비를 강화하되, 상한을 둬서 고점은 유지.
   - 설계 시점: Phase 4 (장비 대개편)에서 구체 수치·UI 설계.

3. **전투 시작 방식:** **방 진입 시 전원 어그로(느낌표) 명료화만 채택.** 아이작식 *강제 클리어(방 잠금)는 보류.*
   - 이유: 강제 클리어는 우회·잠입·회피를 막아 탐험 자유와 충돌. 인텐트 표시 체감 후 재결정.
   - 문 걸침 기습 같은 비직관적 전투 개시는 제거.

4. **기습 시스템:** **완전 삭제 + 보완책 신규 설계.**
   - 기존 기습(시야/문 기준)은 비직관적이라 제거. 로그/어쌔신 정체성을 보존할 새 메커니즘을 설계해 함께 도입.
   - 적 회피 수치는 비슷한 수준으로 정규화(유독 높은 적만 하향).

---

## 🗺️ 단계별 로드맵

### Phase 0 — 정보 표시 (안전한 첫걸음)
- [ ] **0.1** 정확성/회피 수치를 캐릭터 스탯창(`WndHero` StatsTab)에 표시

### Phase 1 — 전투 가독성 (정보 제공 원칙)
- [ ] **1.1** 적 인텐트 표시 (다음 행동 + 데미지 범위, 슬더스/Into the Breach 식)
  - 표시 가능: 데미지 범위, 다음 행동(공격/이동). 표시 불가(확률): 명중/회피 성공 여부.

### Phase 2 — 씨앗 / 돌 / 새총 클러스터
- [ ] **2.1** 돌(Runestone) 시스템 완전 삭제 (12 스톤 + 룬스톤방 2종 + Generator/Level/Shop 참조 정리)
- [ ] **2.2** 새총(Slingshot) 무기 신규 — 씨앗을 발사, 적중 시 씨앗 효과 발현 / 자신에게 쏘면 버프
- [ ] **2.3** 기본 퀵슬롯 세팅 (좌:이슬통 / 중:새총 / 우:투척돌)
- [ ] **2.4** 이슬 보호막: 최대 체력일 때 이슬 섭취 시 보호막 (현재 `SHIELDING_DEW` 특성 한정 → 기본화). 무한 보호막 악용 방지 설계 포함.

### Phase 3 — 전투 구조 개편
- [ ] **3.1** 기습 시스템 완전 삭제 (13개 파일 참조 정리)
- [ ] **3.2** 기습 보완책 신규 설계 (은신 정체성 보존, 단 "보이게/직관적으로")
- [ ] **3.3** 방 진입 시 전원 어그로 명료화
- [ ] **3.4** 적 회피 수치 정규화

### Phase 4 — 장비 대개편 (가장 큼)
- [ ] **4.1** 골드 강화 + 상한선 시스템
- [ ] **4.2** 방어구 다양화 (현재 사실상 1계열)
- [ ] **4.3** 힘 요구치(10/12/14…) 재설계
- [ ] **4.4** 무기 종류 확충
- [ ] **4.5** (히든) 5티어 장비 융합 → 엑스칼리버 등

### 유지 (현재 건드리지 않음)
특성(1.3, 호환성), 물약·주문서 구역당 개수, 장신구·유물·연금술, 보스 보상(추후), 아트·사운드(추후).

---

## 📌 진행 상황

| Phase | 상태 | 비고 |
|-------|------|------|
| Phase 0 | ⬜ 시작 전 | 다음 작업: 0.1 정확성/회피 표시 |
| Phase 1~4 | ⬜ 대기 | - |

기준점: 원작 v3.3.8 (`fork-start` 태그, 커밋 `7b8b845a7`).

---

## 🧭 코드 맵 (조사 완료 — 재탐색 불필요)

경로 접두사: `core/src/main/java/com/shatteredpixel/shatteredpixeldungeon/`

### 스탯 / 레벨링 / UI
- `actors/hero/Hero.java`
  - `earnExp()` (~1967): 레벨업 진입. 레벨업 시 `attackSkill++`, `defenseSkill++`, `updateHT()`. **힘은 안 오름** (물약 전용).
  - `attackSkill(Char)` (~505) / `defenseSkill(Char)` (~562): 최종 정확성/회피를 정수로 반환.
  - 기본값: `attackSkill=10`(213), `defenseSkill=5`(214). HP: `HT = 20 + 5*(lvl-1)` (257).
- `actors/Char.java` `hit()` (~624): 명중 판정. `acuRoll = Random.Float(acuStat)` vs `defRoll`. 명중 시 `acuRoll >= defRoll`.
- `windows/WndHero.java` `StatsTab.initialize()` (~138-214), `statSlot(label,value)` (~216): 스탯 표시 UI. **여기 EXP 줄(~195) 뒤에 정확성/회피 추가.**

### 장비
- `items/weapon/Weapon.java` `STRReq(tier,lvl)` (~360): `(8 + tier*2) - 강화감소`. `items/weapon/melee/MeleeWeapon.java` `tier`(246), 데미지(249-258).
- `items/armor/Armor.java` `tier`(123), `STRReq`(~698). 방어구는 Cloth/Leather/Mail/Scale/Plate = **티어만 다른 1계열** 확정.
- `items/Item.java` `upgrade()` (~401): `level++`. `items/scrolls/ScrollOfUpgrade.java`.
- `Dungeon.java` `LimitedDrops.UPGRADE_SCROLLS`(~107), `souNeeded()` (~545): **5층당 3개** 강화주문서 제한. `levels/Level.java` (~220) 스폰.

### 전투 / 기습 / 몹 AI
- `actors/Char.java` `attack()` (~368), `hit()` (~624). 기습 보너스: `hit()` ~632 (`invisible>0 && canSurpriseAttack()` → `INFINITE_ACCURACY`).
- `actors/mobs/Mob.java`
  - `surprisedBy()` (~775): 기습 정의. 기습 시 `defenseSkill()`→0 (~698).
  - AI 상태: SLEEPING/WANDERING/HUNTING/INVESTIGATING/FLEEING/PASSIVE (~112, 1070+). 깨우기 `awaken()` (~1139), 탐지 `detectionChance` (~1170).
- 기습 참조 파일(삭제 대상): `Char.java`, `Mob.java`, `Dagger.java`, `ThrowingKnife.java`, `Kunai.java`, `AssassinsBlade.java`, `Mace.java`, `Dirk.java`, `MagesStaff.java`, `MasterThievesArmband.java`, `Talent.java`(SUCKER_PUNCH ~879), `FloatingText.java`.
- 높은 회피 몹(정규화 대상): Monk(30, Focus시 무한회피), Snake(25), Succubus(25), Scorpio(24), DwarfKing/RipperDemon(22), Elemental/Eye/Ghoul/YogFist(20).

### 씨앗 / 돌 / 퀵슬롯 / 투척 / 이슬
- `plants/Plant.java` `Seed` 내부클래스(~130-249), 14종 씨앗(`plants/*.java`). 씨앗 효과는 각 Plant의 `activate()`에 히어로/적 분기 존재.
- `items/stones/Runestone.java` + 12 StoneOf*. 룬스톤방: `levels/rooms/special/RunestoneRoom.java`, `.../secret/SecretRunestoneRoom.java`. 등록: `items/Generator.java` STONE 카테고리(~380).
- `QuickSlot.java`(루트, SIZE=6/UI 3), `ui/QuickSlotButton.java`. 기본 슬롯 세팅: `actors/hero/HeroClass.java` 각 `initX()` (~174-261).
- `items/weapon/missiles/MissileWeapon.java`: 투척 흐름 `onThrow(cell)` (~266) → `rangedHit()` (~404). 예시 `ThrowingStone.java`.
- `items/Dewdrop.java` `consumeDew()` (~82-129): 이슬 회복. **`SHIELDING_DEW` 특성 시 초과분 보호막**(이미 구현). 보호막 버프: `actors/buffs/Barrier.java`.
