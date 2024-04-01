package com.unnoba.socialclub.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.unnoba.socialclub.entities.Charge;
import com.unnoba.socialclub.entities.Member;
import com.unnoba.socialclub.entities.Usuario;
import com.unnoba.socialclub.services.ChargeService;
import com.unnoba.socialclub.services.MemberService;
import com.unnoba.socialclub.services.UsuarioServicio;

@Controller
public class HomeController {

	private final MemberService memberService;
	private final ChargeService chargeService;
	private final UsuarioServicio usuarioServicio;
	private String rol;

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Autowired
	public HomeController(MemberService memberService, ChargeService charservice, UsuarioServicio usuarioServicio) {
		this.memberService = memberService;
		this.chargeService = charservice;
		this.usuarioServicio = usuarioServicio;
	}

	@GetMapping("/dashboard")
	public String goHome(Model model, Authentication authentication) {
		String username = authentication.getName();
		Usuario usuario = usuarioServicio.findByEmail(username);
		setRol(usuario.getRol_id().getNombre());
		model.addAttribute("titulo", "Bienvenido/a, " + usuario.getNombre());

		return "dashboard";
	}

	@GetMapping("/socios")
	public String goMembers(Model model, @Param("key") String key, @Param("surnameKey") String surnameKey,
			@Param("idKey") String idKey) {

		if ("ROL_USUARIO".equalsIgnoreCase(getRol())) {
			return "permissionDenied";
		}
		model.addAttribute("titulo", "Listado de socios");
		List<Member> memberList = memberService.getAllMembers(key, surnameKey, idKey);

		model.addAttribute("socios", memberList);
		model.addAttribute("key", key);
		model.addAttribute("surnameKey", surnameKey);
		model.addAttribute("idKey", idKey);

		return "listMembers";
	}

	@GetMapping("/socios-pendientes")
	public String goDebtMembers(Model model, @Param("key") String key, @Param("surnameKey") String surnameKey,
			@Param("idKey") String idKey, @Param("addressKey") String addressKey) {

		model.addAttribute("titulo", "Socios pendientes de pago");
		List<Member> memberList = memberService. getAllMembersWithoutLifeMembers(key, surnameKey, idKey, addressKey);

		model.addAttribute("socios", memberList);
		model.addAttribute("key", key);
		model.addAttribute("surnameKey", surnameKey);
		model.addAttribute("idKey", idKey);

		return "debtMember";
	}

	@GetMapping("/socios-vitalicios")
	public String goLifeMembers(Model model, @Param("key") String key, @Param("surnameKey") String surnameKey,
			@Param("idKey") String idKey) {
		if ("ROL_USUARIO".equalsIgnoreCase(getRol())) {
			return "permissionDenied";
		}
		model.addAttribute("titulo", "Socios vitalicios");
		List<Member> memberList = memberService.findLifeMembers(key, surnameKey, idKey);

		model.addAttribute("socios", memberList);
		model.addAttribute("key", key);
		model.addAttribute("surnameKey", surnameKey);
		model.addAttribute("idKey", idKey);

		return "lifeMembers";
	}

	@RequestMapping("/nuevo-socio")
	public String showMemberAdd(Model model) {
		if ("ROL_USUARIO".equalsIgnoreCase(getRol())) {
			return "permissionDenied";
		}
		model.addAttribute("titulo", "Alta de socio");
		Member socio = new Member();
		model.addAttribute("nuevoSocio", socio);
		return "newMember";
	}

	@RequestMapping(value = "/guardar-socio", method = RequestMethod.POST)
	public String saveMember(@ModelAttribute("socio") Member socio) {

		memberService.saveMember(socio);
		return "redirect:/socios";
	}

	@RequestMapping(value = "/guardar-charge", method = RequestMethod.POST)
	public String saveCharge(@ModelAttribute("charge") Charge charge) {
		chargeService.saveCharge(charge);
		return "redirect:/cobrar-socio";
	}

	@GetMapping("/eliminar/{id}")
	public String deleteMember(@PathVariable int id) {
		memberService.deleteMember(id);
		return "redirect:/socios";
	}

	@RequestMapping("/editar/{id}")
	public ModelAndView showFormEdit(@PathVariable(name = "id") int id) {
		ModelAndView modelo = new ModelAndView("editMember");
		Optional<Member> socio = memberService.findById(id);
		modelo.addObject("socio", socio);
		return modelo;
	}

	@GetMapping("/cobrar-socio")
	public String goCharge(Model model, @Param("key") String key, @Param("surnameKey") String surnameKey,
			@Param("idKey") String idKey, @Param("addressKey") String addressKey) {

		model.addAttribute("titulo", "Cobranzas");
		List<Member> memberList = memberService.getAllMembersWithoutLifeMembers(key, surnameKey, idKey, addressKey);
		model.addAttribute("socios", memberList);

		return "chargeMembers";
	}

	@RequestMapping("/cobrar/{id}")
	public ModelAndView showFormCharge(@PathVariable(name = "id") int id) {
		ModelAndView modelo = new ModelAndView("addChargeMember");
		Member socio = memberService.findByMemberId(id);
		Charge charge = new Charge();
		charge.setMember_id(socio);
		modelo.addObject("charge", charge);
		return modelo;
	}

	@GetMapping("/historial/{id}")
	public String goHistory(Model model, @PathVariable(name = "id") int id) {

		model.addAttribute("titulo", "Historial de socio");

		Member member = memberService.findByMemberId(id);
		List<Charge> charges = memberService.memberCharges(member);

		model.addAttribute("charges", charges);

		return "historyMember";
	}

	@GetMapping("/comprobante/{id}")
	public String chargeMember(Model model, @PathVariable(name = "id") int id) {

		model.addAttribute("titulo", "Comprobante");
		Charge charge = chargeService.findChargeById(id);
		model.addAttribute("charge", charge);
		return "showCharge";
	}

	@GetMapping("/ver/{id}")
	public String showMember(Model model, @PathVariable(name = "id") int id) {

		model.addAttribute("socio", memberService.findByMemberId(id));
		return "showMember";
	}

	@GetMapping("/login")
	public String iniciarSesion() {

		return "login";
	}

}
